class Application extends React.Component {
  constructor() {
    super();
    this.state = {
      page: 0,
      runs: [],
      student: 1
    }
  }
  /**
   * Render the navigation bar.
   */
  renderNav() {
    return(
      <div>
        <div className="row justify-content-end top">
        <div className="col-6">Plagiarism Checker</div>
          <div className="col-3"> { readCookie('UserName') == null ? "" : "Welcome " + readCookie('UserName') }</div>
          <div className="col-2">
            { readCookie('UserName') == null ? "" :
              <button type="button" className="btn btn-danger logout" onClick={ () => this.logout() }>Log out</button> }
          </div>
        </div>
      </div>
    );
  }

  /**
   * Render the banner.
   */
  renderBanner() {
    return(
      <div className="row justify-content-center">
        <div className="col-md-auto indexBanner">
          <button className={ this.state.page == 0 ? "clickedButton" : "unclickedButton" }
            onClick={ () => this.setState({ page: 0 }) }>Log In</button>
        </div>
        <div className="col-md-auto indexBanner">
          <button className={ this.state.page == 1 ? "clickedButton" : "unclickedButton" }
            onClick={ () => this.setState({ page: 1 }) }>Register</button>
        </div>
      </div>
    );
  }

  /**
   * Render the register UI.
   */
  renderRegistration() {
    return (
      <div>
        { this.renderNav() }
        <div className="container">
          { this.renderBanner() }
          <div id="indexcontain">
            <form onSubmit={ e => this.onRegistrationSubmit(e) }>
              <div className="row justify-content-center form">
                <div className="col-md-auto"><input type="email" id="email" placeholder="Email"/></div>
              </div>
              <div className="row justify-content-center form">
                <div className="col-md-auto"><input type="password" id="pwd" placeholder="Password"/></div>
              </div>
              <div className="row justify-content-center form">
                <div className="col-md-auto"><input type="password" id="crfmpwd" placeholder="Confirm Password"/></div>
              </div>
              <div className="row justify-content-center form">
                <div className="col-md-auto">
                  <button type="submit" className="btn btn-primary subbtn" title="Warning!" data-container="body"
                    data-toggle="popover" data-placement="right" data-content="Check password!">Submit</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    );
  }

  /**
   * Register a user.
   */
  onRegistrationSubmit(e) {
    e.preventDefault();

    // Check if two passwords are same, or if there is one empty
    if ($("#pwd").val() != $("#crfmpwd").val() || $("#pwd").val() == "" || $("#crfmpwd").val() == "") {
      $('[data-toggle="popover"]').popover('show'); 
      setTimeout(function() {
        $('[data-toggle="popover"]').popover('hide'); 
      }, 2000);
      return;
    }

    // Disable the Bootstrap popover
    $('[data-toggle="popover"]').popover('hide');

    // Send the data to the end point
    var data = new FormData();
    data.append('username', $("#email").val()); 
    data.append('password', $("#pwd").val());
    data.append('role', 'PROFESSOR');
    fetch('/registration', {
      method: 'POST',
      body: data
    });
    this.setState({ page: 0 });
  }

  /**
   * Render the login UI.
   */
  renderLogin() {
    return (
      <div>
        { this.renderNav() }
        <div className="container">
          { this.renderBanner() }
          <div id="indexcontain">
            <form onSubmit={ e => this.onLoginSubmit(e) }>
              <div className="row justify-content-center form">
                <div className="col-md-auto"><input type="email" id="email" placeholder="Email"/></div>
              </div>
              <div className="row justify-content-center form">
                <div className="col-md-auto"><input type="password" id="pwd" placeholder="Password"/></div>
              </div>
              <div className="row justify-content-center form">
                <div className="col-md-auto">
                  <button type="submit" className="btn btn-primary subbtn" title="Warning!" data-container="body"
                    data-toggle="popover" data-placement="right" data-content="Invalid credential!">Submit</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    );
  }

  /**
   * User logs in.
   */
  onLoginSubmit(e) {
    e.preventDefault();

    // Form the JSON data which needs to be sent
    var that = this;
    var xhr = new XMLHttpRequest();
    var data = JSON.stringify({
      "username": $("#email").val(),
      "password": $("#pwd").val()
    });
    xhr.withCredentials = true;

    // Create a listener for setting cookie 
    xhr.addEventListener("readystatechange", function() {
      if (this.readyState === 4) {
        if (this.status == 200) {
          var datan = null;
          var xhrn = new XMLHttpRequest();
          xhrn.withCredentials = true;
          createCookie('UserName',this.getResponseHeader('User'));
          createCookie('Authorization',this.getResponseHeader('Authorization'));

          // Add another listener for getting response.
          xhrn.addEventListener("readystatechange", function() {
            if (this.readyState === 4) {
              $('[data-toggle="popover"]').popover('hide');
              createCookie('uid', this.responseText);
              that.setState({ page: 3 })
            }
          });

          // Set cookie
          xhrn.open("GET", "/user?userName=" + readCookie('UserName'));
          xhrn.setRequestHeader("Authorization", readCookie('Authorization'));
          xhrn.setRequestHeader("Cache-Control", "no-cache");
          xhrn.send(datan);
        } else {
          $('[data-toggle="popover"]').popover('show'); 
          setTimeout(function() {
            $('[data-toggle="popover"]').popover('hide'); 
          }, 2000);
          return;
        }
      }
    });

    // Send data
    xhr.open("POST", "/login");
    xhr.setRequestHeader("Cache-Control", "no-cache");
    xhr.send(data);
  }

  /**
   * Render the plagiarism check UI.
   */
  renderPlagiarismCheck() {
    return(
      <div>
        { this.renderNav() }
        <div className="container pcheck">
          <div className="row justify-content-md-center">
            <div className="col-3 runs">{ this.renderSideColumn() }</div>
            <div className="col statistics">
              <h4 id="title">{ this.state.runs.length == 0 ? "Currently there are no runs." : "Select a run and check." }</h4>
              <div className="container" id="sa">
              </div>
              <div id="chart"></div>
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
                <div id="students"></div><hr/>  
                <input type="text" id="rundescription" placeholder="Run Description"/>
                <div id="advanced"><hr />
                  <div className="container">
                    <div className="row">
                      <div className="col"><p>How do you want the startegies weighted?</p></div>
                    </div>
                    <div className="row">
                      <div className="col-3">Levenshtein</div>
                      <div className="col-md-auto"><div id="slider1"></div></div>
                      <div className="col-md-auto"><div id="value1"></div></div>
                    </div>
                    <div className="row">
                      <div className="col-3">LCS</div>
                      <div className="col-md-auto"><div id="slider2"></div></div>
                      <div className="col-md-auto"><div id="value2"></div></div>
                    </div>
                    <div className="row">
                      <div className="col-3">Cosine</div>
                      <div className="col-md-auto"><div id="slider3"></div></div>
                      <div className="col-md-auto"><div id="value3"></div></div>
                    </div>
                  </div>   
                </div>
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-primary" onClick={ () => this.advancedSettings() }>Advanced</button>
                <button type="button" className="btn btn-primary" onClick={ () => this.runCheck() } title="Information" data-container="body"
                  data-toggle="popover" data-placement="right" data-content="Check started, close the window">Run</button>
                <button type="button" className="btn btn-primary" onClick={ () => this.addStudent() }>Add Student</button>
                <button type="button" className="btn btn-default" data-dismiss="modal">Cancel</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }

  /**
   * Render the plagiarism check side column.
   * 
   * <button type="button" className="btn btn-primary" onClick={ () => this.showStatistic(0) }>Run 0</button>
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
    $('#slider1').slider({ min: 0, max: 100, value: 50 });
    $('#slider1').change(function(slideEvt, ui) {
      $('#value1').html($('#slider1').slider('getValue') + "%");
    });
    $('#value1').html($('#slider1').slider('getValue') + "%");
    $('#slider2').slider({ min: 0, max: 100, value: 50 });
    $('#slider2').change(function() {
      $('#value2').html($('#slider2').slider('getValue') + "%");
    });
    $('#value2').html($('#slider2').slider('getValue') + "%");
    $('#slider3').slider({ min: 0, max: 100, value: 50 });
    $('#slider3').change(function() {
      $('#value3').html($('#slider3').slider('getValue') + "%");
    });
    $('#value3').html($('#slider3').slider('getValue') + "%");
    $('#advanced').show();
  }

  /**
   * Log out.
   */
  logout() {
    eraseCookie('UserName');
    eraseCookie('Authorization');
    eraseCookie('uid');
    this.setState({ page: 0 });
  }

  /**
   * Add a new student to the modal.
   */
  addStudent() {
    var stu = this.state.student;
    var newGit = "<input type='text' class='git'" + " id='" + stu.toString() + "' placeholder='Student " + stu.toString() + " GitHub Link'/>"
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

    // Append all Git Hub links together
    var links = [];
    for (var i = 1; i < this.state.student; i++) {
      var id = "#" + i.toString();
      links.push($(id).val())
    }

    // Append all data together
    var data = new FormData();
		data.append("description", $("#rundescription").val()); 
		data.append("gitUrls", links);
    data.append("sharedUsers", []);
    
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
        }, 2000);
        
        // Update the state
        var data = null;
        var loadRuns = [];
        var url = "/report/userId/" + readCookie('uid');
        var xhr = new XMLHttpRequest();
        xhr.withCredentials = true;
        xhr.addEventListener("readystatechange", function() {
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
    
    var sim = 0;
		xhr.addEventListener("readystatechange", function () {
			if (this.readyState === 4) {
        var result = JSON.parse(this.responseText);
        var showing = "";
        $("#title").text("Statistics of " + r.toString());
    
        
        for (var i = 0; i < result.reportFile.comparisonList.length; i++) {

          var row = "<div class='row'>";
          row += "<div class='btn-group'>";
          row += "<button type='button' class='btn btn-default dropdown-toggle' data-toggle='dropdown'>";
          row += result.reportFile.comparisonList[i].filename1 + " vs. " + result.reportFile.comparisonList[i].filename2 + "<span class='caret'></span>"
          row += "</button><ul class='dropdown-menu'>";
          row += "sa";
          row += "</ul></div></div>";
          showing += row;

          /*
          var row = "<div className='row'>";
          row += "<div className='col-2'>"
          row += result.reportFile.comparisonList[i].filename1;
          row += "</div>";
          row += "<div className='col-2'>"
          row += result.reportFile.comparisonList[i].filename2;
          row += "</div>";
          row += "<div className='col-2'>"
          row += result.reportFile.comparisonList[i].scores.totalScore;
          sim = result.reportFile.comparisonList[i].scores.totalScore;
          row += "</div>";
          row += "<div className='col-2'>"
          row += result.reportFile.comparisonList[i].scores.subScores;
          row += "</div>";
          row += "</div>";
          showing += row;
          */
        }
        $("#sa").html(showing);
        

/*
        var myChart = echarts.init(document.getElementById('chart'));

        // 指定图表的配置项和数据
        var option = {
          tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data:['Similar','Diff']
        },
        series: [
            {
                name:'Sta',
                type:'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '30',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data:[
                    {value:Number(sim), name:'Similar'},
                    {value:1-Number(sim), name:'Diff'}
                ]
            }
        ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);*/

			}
		});
		xhr.open("GET", endPoint);
		xhr.setRequestHeader("Cache-Control", "no-cache");
		xhr.setRequestHeader("Authorization", document.cookie);
		xhr.send(data);
  }

  /**
   * Render the main UI.
   */
  render() {
    if (readCookie('UserName') != null && readCookie('UserName') != "") {
      return(this.renderPlagiarismCheck());
    } else {
      if (this.state.page == 1) {
        return(this.renderRegistration());
      } else {
        return(this.renderLogin());
      }
    }
  }
}

ReactDOM.render(
	<Application />,
	document.getElementById('root')
);