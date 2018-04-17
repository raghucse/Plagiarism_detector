class Dashboard extends React.Component {
  constructor() {
    super();
    this.state = {
      runs: [],
      student: 1,
      runID: "",
      runDescription: "",
      showModal: true
    }

    var data = null;
    var that = this;
    var loadRuns = [];
    var url = "/report/userId/" + readCookie('uid')
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;
    xhr.addEventListener("readystatechange", function () {
      if (this.readyState === 4) {
        loadRuns = JSON.parse(this.responseText);          
        that.setState({ runs: loadRuns });
      }
    });		
    xhr.open("GET", url);
    xhr.setRequestHeader("Cache-Control", "no-cache");
    xhr.setRequestHeader("Authorization", readCookie('Authorization'));
    xhr.send(data);
  }

  /**
   * Render the plagiarism check UI.
   */
  render() {
    return(
      <div>
        <div className="container pcheck">
          <div className="row justify-content-md-center">
            <div className="col-3 runs">{ this.renderSideColumn() }</div>
            <div className="col statistics">
              <h4 id="title">{ this.state.runs.length == 0 ? "Currently there are no runs." : "Select a run and check." }</h4>
              <div className="container" id="sa"></div>
            </div>
          </div>
        </div>
        <div className="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <h5 className="modal-title" id="myModalLabel">Add New Run</h5>
                <button type="button" className="close" data-dismiss="modal" aria-hidden="true">&times;</button>
              </div>
              <div className="modal-body">
                <h5>Step 1: Add Student (GitHub Repo)<button type="button" className="btn btn-primary add_student" onClick={ () => this.addStudent() }>Add Student</button></h5>
                Example: https://github.com/user/exampleRepo.git
                <div id="students"></div><hr/>
                <h5>Step 2: Define Run<button type="button" className="btn btn-primary advanced" disabled={ this.disableStep2('btn') } onClick={ () => this.advancedSettings() }>Advanced</button></h5>
                  <div className="row">
                    <div className="col-3 step2label">Description:</div>
                    <div className="col">
                      <input type="text" id="rundescription" placeholder={ this.disableStep2('btn') ? "Add some students first" : "Run Description" }
                        disabled={ this.disableStep2('text') } onChange={ () => this.setState({ runDescription: $('#rundescription').val() }) }/>
                    </div>
                  </div>
                  <div id="advanced"><hr />
                    <h5>Step 3: Adjust Startegies Weight
                      <button type="button" className="btn btn-primary advanced" onClick={ () => this.resetSliders() }>Reset</button>
                    </h5>
                    <div className="container">
                      <div className="row advancedSlider">
                        <div className="col-3">Levenshtein</div>
                        <div className="col-md-auto"><div id="slider1"></div></div>
                        <div className="col-md-auto"><div id="value1"></div></div>
                      </div>
                      <div className="row advancedSlider2">
                        <div className="col-3">LCS</div>
                        <div className="col-md-auto"><div id="slider2"></div></div>
                        <div className="col-md-auto"><div id="value2"></div></div>
                      </div>
                      <div className="row advancedSlider3">
                        <div className="col-3">Cosine</div>
                        <div className="col-md-auto"><div id="slider3"></div></div>
                        <div className="col-md-auto"><div id="value3"></div></div>
                      </div>
                    </div><hr />   
                  </div>
                  <div className="row">
                    <div className="col">
                      <button type="button" className="btn btn-primary runCheck" onClick={ () => this.runCheck() } title="Information" data-container="body" disabled={ this.disableFinalRun() }
                        data-toggle="popover" data-placement="right" data-content="Check started, the window will be closed and auto-refreshed in 5 secs.">
                        { this.disableStep2('btn') ? "Add some students first" : this.disableFinalRun() ? "Add run ID and a description" : "Run" }
                      </button>
                    </div>
                  </div>
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-danger cancel" data-dismiss="modal" onClick={ () => this.resetStudent() }>Cancel</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }

  /**
   * Disabling different components for step 2.
   */
  disableStep2(type) {
    var result = this.state.student == 2;
    switch (type) {
      case 'text':
        return result ? "true" : null;
        break;
      case 'btn':
      default:
        return result;
    }
  }

  /**
   * Disabling different components for the final run.
   */
  disableFinalRun() {
    return !(!this.disableStep2('btn') && this.state.runDescription.length != 0);
  }

  /**
   * Reset students for the cancel button in modal.
   */
  resetStudent() {
    this.setState({ student: 1 });
    document.getElementById('students').innerHTML = null;
    $('#runid').val('');
    $('#rundescription').val('');
    $('#sharedusers').val('');
    $("#slider1").val(34).slider("refresh");
    $("#slider2").val(33).slider("refresh");
    $("#slider3").val(33).slider("refresh");
  }

  /**
   * Render the plagiarism check side column.
   */
  renderSideColumn() {
    const runElements = [];
    for (let r of this.state.runs) {
      runElements.push(
        <div className="row runelems">
          <div className="col sider">
            <button type="button" className="btn btn-info btn-lg runbtn" onClick={ () => this.showStatistic(r) }>Run { r }</button>
          </div>
        </div>
      );
    }

    return(
      <div className="container">
        <div className="row">
          <div className="col sider">
            <button type="button" className="btn btn-danger btn-lg" data-toggle="modal" data-target="#myModal"
            onClick={ () => $("#advanced").hide() }>Add New Run</button>
            <hr />Run History<hr />
          </div>
        </div>
        { runElements }
      </div>
    );
  }

  /**
   * Advanced settings in modal.
   */
  advancedSettings() {
    if (this.state.showModal) {
      $('#slider1').slider({ min: 0, max: 100, step: 1, value: 33 });
      $('#slider2').slider({ min: 0, max: 100, step: 1, value: 33 });
      $('#slider3').slider({ min: 0, max: 100, step: 1, value: 33 });

      var slider1val = $('#slider1').slider('getValue');
      var slider2val = $('#slider2').slider('getValue');
      var slider3val = $('#slider3').slider('getValue');

      $('#slider1').change(function(slideEvt, ui) {
        var diff = $(this).slider('getValue') - slider1val;
        $('#slider2').slider('refresh');
        $('#slider2').slider('setValue', slider2val - diff / 2, true);
        $('#slider3').slider('refresh');
        $('#slider3').slider('setValue', slider3val - diff / 2, true);
        slider1val = $('#slider1').slider('getValue');
        slider2val -= diff / 2;
        slider3val -= diff / 2;
        $('#value1').html($('#slider1').slider('getValue') + "%");
        $('#value2').html($('#slider2').slider('getValue') + "%");
        $('#value3').html($('#slider3').slider('getValue') + "%");
      });
      $('#value1').html($('#slider1').slider('getValue') + "%");

      $('#slider2').change(function() {
        var diff = $(this).slider('getValue') - slider2val;
        $('#slider1').slider('refresh');
        $('#slider1').slider('setValue', slider1val - diff / 2, true);
        $('#slider3').slider('refresh');
        $('#slider3').slider('setValue', slider3val - diff / 2, true);
        slider2val = $('#slider2').slider('getValue');
        slider1val -= diff / 2;
        slider3val -= diff / 2;
        $('#value1').html($('#slider1').slider('getValue') + "%");
        $('#value2').html($('#slider2').slider('getValue') + "%");
        $('#value3').html($('#slider3').slider('getValue') + "%");
      });
      $('#value2').html($('#slider2').slider('getValue') + "%");
      
      $('#slider3').change(function() {
        var diff = $(this).slider('getValue') - slider3val;
        $('#slider1').slider('refresh');
        $('#slider1').slider('setValue', slider1val - diff / 2, true);
        $('#slider2').slider('refresh');
        $('#slider2').slider('setValue', slider2val - diff / 2, true);
        slider3val = $('#slider3').slider('getValue');
        slider1val -= diff / 2;
        slider2val -= diff / 2;
        $('#value1').html($('#slider1').slider('getValue') + "%");
        $('#value2').html($('#slider2').slider('getValue') + "%");
        $('#value3').html($('#slider3').slider('getValue') + "%");
      });
      $('#value3').html($('#slider3').slider('getValue') + "%");
      $('#advanced').show();
      this.setState({ showModal: false });
    } else {
      $('#advanced').hide();
      this.setState({ showModal: true });
    }
  }

  /**
   * Reset all sliders
   */
  resetSliders() {
    $("#slider1").val(50).slider("refresh");
    $("#slider2").val(50).slider("refresh");
    $("#slider3").val(50).slider("refresh");
    $('#value1').html($('#slider1').slider('getValue') + "%");
    $('#value2').html($('#slider2').slider('getValue') + "%");
    $('#value3').html($('#slider3').slider('getValue') + "%");
  }

  /**
   * Add a new student to the modal.
   */
  addStudent() {
    var stu = this.state.student;
    var newGit = "<div id='stu" + stu.toString() + "'>"
    newGit += "<input type='text' class='git'" + " id='hw" + stu.toString() + "' placeholder='Student " + stu.toString() + " GitHub Link'/> ";
    newGit += "<input type='text' class='nm'" + " id='nm" + stu.toString() + "' placeholder='Student " + stu.toString() + " Name'/> ";
    newGit += "</div>";
    $("#students").append(newGit);
    stu += 1;
    this.setState({ student: stu });
  }

  /**
	 * Run the checks.
	 */
  runCheck() {
    
    console.log(readCookie('Authorization'));
    console.log(readCookie('uid'));

    var res = true;
    for (var i = 1; i < this.state.student; i++) {
      res &= $("#hw" + i.toString()).val() != "";
      res &= /^https:\/\/github\.com[a-zA-Z0-9\/]+\.git$/.test($("#hw" + i.toString()).val());
      res &= $("#nm" + i.toString()).val() != "";
    }

    if (!res) {
      $('[data-toggle="popover"]').attr('data-content', 'Incomplete student information or invalid GitHub links!')
      $('[data-toggle="popover"]').popover('show'); 
      setTimeout(function() {
        $('[data-toggle="popover"]').popover('hide'); 
      }, 5000);
      $('[data-toggle="popover"]').attr('data-content', 'Check started, the window will be closed and auto-refreshed in 5 secs!')
      return;
    }

    // Disable run button and close the window
    $(".runCheck").attr("disabled", true);
    $(".cancel").attr("disabled", true);
    setTimeout(function() {
      $('#myModal').modal('hide');
    }, 5000);
    
    // Append all GitHub links & student names together
    var links = [];
    for (var i = 1; i < this.state.student; i++) {
      var id = "#hw" + i.toString();
      links.push($(id).val())
    }
    var names = [];
    for (var i = 1; i < this.state.student; i++) {
      var nm = "#nm" + i.toString();
      names.push($(nm).val())
    }

    // Append all data together
    var data = new FormData();
    data.append("runID", $('#runid').val());
    data.append("createdUserID", readCookie('uid'));
    data.append("description", $("#rundescription").val()); 
    data.append("sharedUsers", []);

    var weight = [];
    $('#slider1').val().length != 0 ? weight.push($('#slider1').val() / 100) : weight.push(0.34);
    $('#slider2').val().length != 0 ? weight.push($('#slider2').val() / 100) : weight.push(0.33);
    $('#slider3').val().length != 0 ? weight.push($('#slider3').val() / 100) : weight.push(0.33);
    data.append("strategiesWeight", weight);
    data.append("strategiesNames", ["LEVENSHTEIN", "LCS", "COSINE"]);
    data.append("gitUrls", links);
    data.append("studentNames", names);
    
    // Post to end point
    var xhr = new XMLHttpRequest();
    var that = this;
		xhr.withCredentials = true;
		xhr.addEventListener("readystatechange", function () {
			if (this.readyState === 4) {
        var text = this.responseText; 
               
        $('[data-toggle="popover"]').popover('show'); 
        setTimeout(function() {
          $('[data-toggle="popover"]').popover('hide'); 
        }, 5000);

        that.setState({
          student: 1
        });
        window.location.reload();
			}
		});
		xhr.open("POST", "/plagiarism/run");
		xhr.setRequestHeader("Cache-Control", "no-cache");
		xhr.setRequestHeader("Authorization", document.cookie);
    xhr.send(data);
  }

  /**
   * Show the statistic of a certain run.
   */
  showStatistic(r) {
    var data = null;
		var endPoint = "report/reportId/" + r.toString();
		var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;
    
		xhr.addEventListener("readystatechange", function () {
			if (this.readyState === 4) {
        var result = JSON.parse(this.responseText);
        console.log(this.responseText);
        
        $("#title").text("Statistics of " + r.toString());
    
        var finalShowing = "<div class='row'><p>Run Description: " + "Sample Description" + "</p></div>";
        finalShowing += "<table class='table'><thead><tr>" + 
                        "<th>Student1</th><th>Student2</th><th>File1</th><th>File2</th><th>Percentage</th><th>Severity</th><th>GitDiff</th>" +
                        "</tr></thead><tbody>";

        for (var i = 0; i < result.reportFile.comparisonList.length; i++) {
          var row = "<tr>";
          row += "<td>" + "Student1" + "</td>";
          row += "<td>" + "Student2" + "</td>";
          row += "<td>" + result.reportFile.comparisonList[i].filename1 + "</td>";
          row += "<td>" + result.reportFile.comparisonList[i].filename2 + "</td>";
          row += "<td>" + Math.floor(result.reportFile.comparisonList[i].scores.totalScore * 100) / 100 + "</td>";

          var _score = Math.floor(result.reportFile.comparisonList[i].scores.totalScore * 100) / 100;
          var se = "";
          if (_score >= 0.8) {
            se = "High";
          } else if (_score >= 0.6 && _score < 0.8) {
            se = "Medium";
          } else {
            se = "Low";
          }

          row += "<td id='" + se + "'>" + se + "</td>";
          row += "<td><div class='dropdown'>" +
                 "<button class='btn btn-primary dropdown-toggle' type='button' id='dropdownMenuButton' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>" +
                 "View</button><div class='dropdown-menu dropdown-menu-right' id='gitdiffdata' aria-labelledby='dropdownMenuButton'>" +
                 result.reportFile.comparisonList[i].scores.subScores;
          row += "</div></div></td></tr>"
          finalShowing += row;
        }
        finalShowing += "</tbody></table>";
        $("#sa").html(finalShowing);
			}
		});
		xhr.open("GET", endPoint);
		xhr.setRequestHeader("Cache-Control", "no-cache");
		xhr.setRequestHeader("Authorization", document.cookie);
    xhr.send(data);
  }
}

ReactDOM.render(
	<Dashboard />
);
