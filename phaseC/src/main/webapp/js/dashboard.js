class Dashboard extends React.Component {
  constructor() {
    super();

    this.state = {
      runs: [],
      runnames: [],
      student: 1,
      runName: "",
      runDescription: "",
      showModal: true
    }

    var data = null;
    var that = this;
    var loadRuns = [];
    var loadNames = [];
    var url = "/report/userId/" + readCookie('uid')
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;
    xhr.addEventListener("readystatechange", function () {
      if (this.readyState === 4) {        
        var temp = JSON.parse(this.responseText);   
        for (var i = 0; i < temp.length; i++) {
          loadRuns.push(temp[i].id);
          loadNames.push(temp[i].runName);
        }
        that.setState({ 
          runs: loadRuns,
          runnames: loadNames
        });
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
                    <div className="col-3 step2label">Run Name:</div>
                    <div className="col">
                      <input type="text" id="runname" placeholder={ this.disableStep2('btn') ? "Add some students first" : "Run Name, 1-8 letters" } disabled={ this.disableStep2('text') }
                         onChange={ () => this.setState({ runName: $('#runname').val() }) }/>
                    </div>
                  </div>
                  <div className="row">
                    <div className="col-3 step2label">Description:</div>
                    <div className="col">
                      <input type="text" id="rundescription" placeholder={ this.disableStep2('btn') ? "Add some students first" : "Run Description" } disabled={ this.disableStep2('text') }
                         onChange={ () => this.setState({ runDescription: $('#rundescription').val() }) }/>
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
                        { this.disableStep2('btn') ? "Add some students first" : this.disableFinalRun() ? "Add run name and a description" : "Run" }
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
    var result = this.state.student <= 2;
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
    return !(!this.disableStep2('btn') && this.state.runDescription.length != 0 && (this.state.runName.length > 0 && this.state.runName.length <= 8));
  }

  /**
   * Reset students for the cancel button in modal.
   */
  resetStudent() {
    this.setState({ student: 1 });
    document.getElementById('students').innerHTML = null;
    $('#runname').val('');
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
    for (var i = 0; i < this.state.runs.length; i++) {
      runElements.push(
        <div className="row runelems">
          <div className="col sider">
            <button type="button" id={this.state.runs[i]} className="btn btn-info btn-lg runbtn" onClick={ this.showStatistic.bind(this, this.state.runs[i]) }>{ this.state.runnames[i] }</button>
          </div>
        </div>
      )
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
      res &= /^https:\/\/github\.com[a-zA-Z0-9\/-]+\.git$/.test($("#hw" + i.toString()).val());
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
    data.append("runID", "");
    data.append("runName", this.state.runName);
    data.append("createdUserID", 0);
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
          window.location.reload(); 
        }, 5000);

        that.setState({
          student: 1
        });
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
  showStatistic(thisStatus, r) {
    
    var data = null;
		var endPoint = "report/reportId/" + thisStatus;
		var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;
		xhr.addEventListener("readystatechange", function () {
			if (this.readyState === 4) {
        var result = JSON.parse(this.responseText);
        
        if (result == null || result.reportFile == null) {
          $("#title").text("Errors in getting report. Probably caused by invalid GitHub repo URL.");
          $("#sa").html("");
          return;
        }
        $("#title").text("Statistics of the run " + result.runName);

        var finalShowing = "<div class='row'><p>Run Description: " + result.description + "</p></div>";
        finalShowing += "<table class='table'><thead><tr>" + 
                        "<th>Student1</th><th>Student2</th><th>File1</th><th>File2</th><th>Percentage</th><th>Severity</th><th>GitDiff</th>" +
                        "</tr></thead><tbody>";
        for (var i = 0; i < result.reportFile.comparisonList.length; i++) {
          var row = "<tr>";
          row += "<td>" + result.reportFile.comparisonList[i].studentname1 + "</td>";
          row += "<td>" + result.reportFile.comparisonList[i].studentname2 + "</td>";
          row += "<td>" + result.reportFile.comparisonList[i].filename1 + "</td>";
          row += "<td>" + result.reportFile.comparisonList[i].filename2 + "</td>";

          var _score = Math.floor(result.reportFile.comparisonList[i].scores.totalScore * 100) / 100;
          row += "<td>" + _score + "</td>";
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
                 "View</button><div class='dropdown-menu dropdown-menu-right' id='gitdiffdata' aria-labelledby='dropdownMenuButton'>";

          var _sS = result.reportFile.comparisonList[i].scores.subScores.split(";");
          var _ssS = "<table id='separ' class='table'><thead class='thead-light'><tr><th id='havetocenter' scope='col' colspan='3'>Seperate Scores of Three Startegies</th>" + 
                     "</tr><tr><th id='havetocenter' scope='col'>LCS</th><th id='havetocenter' scope='col'>LVDistance</th><th id='havetocenter' scope='col'>CosineSimilarity</th></tr></thead>" + 
                     "<tbody><tr><td id='havetocenter'>" + Math.floor(_sS[0].substring(4) * 10000) / 10000 + "</td><td id='havetocenter' >" + 
                     Math.floor(_sS[1].substring(12) * 10000) / 10000 + "</td><td id='havetocenter'>" + Math.floor(_sS[2].substring(18) * 10000) / 10000 + "</td></tr></tbody></table>"; 

          // Check the gitDiff in the first file
          var gitSituation1 = "";
          for (var a = 0; a < result.reportFile.comparisonList[i].diffContent[0].length; a++) {
            var flag = false;
            for (var b = 0; b < result.reportFile.comparisonList[i].diffContent[2].length; b++) {
              if (result.reportFile.comparisonList[i].diffContent[0][a] == result.reportFile.comparisonList[i].diffContent[2][b]) {
                flag = true;
                break;
              }
            }
            if (flag) {
              gitSituation1 += "<p id='gitDuplicate'>" + result.reportFile.comparisonList[i].diffContent[0][a] + "</p>";
            } else {
              gitSituation1 += "<p id='gitNotDuplicate'>" + result.reportFile.comparisonList[i].diffContent[0][a] + "</p>";
            }
          }

          // Check the gitDiff in the second file
          var gitSituation2 = "";
          for (var a = 0; a < result.reportFile.comparisonList[i].diffContent[1].length; a++) {
            var flag = false;
            for (var b = 0; b < result.reportFile.comparisonList[i].diffContent[3].length; b++) {
              if (result.reportFile.comparisonList[i].diffContent[1][a] == result.reportFile.comparisonList[i].diffContent[3][b]) {
                flag = true;
                break;
              }
            }
            if (flag) {
              gitSituation2 += "<p id='gitDuplicate'>" + result.reportFile.comparisonList[i].diffContent[1][a] + "</p>";
            } else {
              gitSituation2 += "<p id='gitNotDuplicate'>" + result.reportFile.comparisonList[i].diffContent[1][a] + "</p>";
            }
          }

          row += "<div class='container'><div class='row justify-content-md-center'>" + _ssS + "</div><p>Comparison of two files, similiar parts are marked as red:</p><div class='row justify-content-md-center gitdiff pre-scrollable'>" + 
                 "<div class='col'>" + gitSituation1 + "</div><div class='col'>" + gitSituation2 + "</div></div></div>";
          row += "</div></div></td></tr>";
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
