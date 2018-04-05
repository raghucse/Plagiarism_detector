class Application extends React.Component {
  constructor() {
    super();

    // Request all report IDs under the user's name
    /*
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
    */

   var loadRuns = [1, 2];
   this.setState({ runs: loadRuns });
    
    /**
     * page: login/register
     * runs: report IDs under the user's name
     * student: number of student when adding Git Repo
     * other attributes: login/register/report cache
     */
    this.state = {
      page: 4,
      admin: 0,
      runs: [],
      student: 1,

      email: "",
      name: "",
      password: "",
      cfrm: "",
      role: "",

      runID: "",
      runDescription: ""
    }
  }
  /**
   * Render the navigation bar UI.
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
   * Render the banner UI.
   */
  renderBanner() {
    return(
      <div className="row justify-content-center">
        <div className="col-md-auto indexBanner">
          <button className={ this.state.page == 0 ? "clickedButton" : "unclickedButton" }
            onClick={ () => this.prepareForLoginOrReg(0) }>{ this.state.page == 0 ? "▶" : "" }Log In</button>
        </div>
        <div className="col-md-auto indexBanner">
          <button className={ this.state.page == 1 ? "clickedButton" : "unclickedButton" }
            onClick={ () => this.prepareForLoginOrReg(1) }>{ this.state.page == 1 ? "▶" : "" }Register</button>
        </div>
      </div>
    );
  }

  /**
   * Erasing cache when switching pages.
   */
  prepareForLoginOrReg(_page) {
    $('#email').val('');
    $('#name').val('');
    $('#pwd').val('');
    $('#crfmpwd').val('');
    $('#email').val('');
    $('[name="role"]:checked').val('');
    this.setState({
      page: _page,
      email: "",
      name: "",
      password: "",
      cfrm: "",
      role: "",
    });
  }

  /**
   * Render the user registration UI.
   */
  renderRegistration() {
    return (
      <div>
        { this.renderNav() }
        <div className="container">
          { this.renderBanner() }
          <div id="indexcontain">
            <div className="row justify-content-center reg_slogan_col">
              <div className="col-md-auto"><span id="reg_slogan">User Registration</span></div>
            </div>
            <form onSubmit={ e => this.onRegistrationSubmit(e) }>
              <div className="row justify-content-center form">
                <div className="col-md-auto index_label"><span className="index_label_text">Email</span></div>
                <div className="col-md-auto"><input type="email" id="email" placeholder="user@example.com" onChange={ () => this.setState({ email: $('#email').val() }) }/></div>
                <div className="col-md-auto index_star"><span className="index_label_text">&nbsp;&nbsp;&nbsp;&nbsp;*</span></div>
              </div>
              <div className="row justify-content-center form">
                <div className="col-md-auto index_label"><span className="index_label_text">Name</span></div>
                <div className="col-md-auto"><input type="text" id="name" placeholder="David Jackson" onChange={ () => this.setState({ name: $('#name').val() }) }/></div>
                <div className="col-md-auto index_star"><span className="index_label_text">&nbsp;&nbsp;&nbsp;&nbsp;*</span></div>
              </div>
              <div className="row justify-content-center form">
                <div className="col-md-auto index_label"><span className="index_label_text">Password</span></div>
                <div className="col-md-auto"><input type="password" id="pwd" placeholder="Password" onChange={ () => this.setState({ password: $('#pwd').val() }) }/></div>
                <div className="col-md-auto index_star"><span className="index_label_text">&nbsp;&nbsp;&nbsp;&nbsp;*</span></div>
              </div>
              <div className="row justify-content-center form">
                <div className="col-md-auto index_label"><span className="index_label_text">Confirm</span></div>
                <div className="col-md-auto"><input type="password" id="crfmpwd" placeholder="Confirm Password" onChange={ () => this.setState({ cfrm: $('#crfmpwd').val() }) }/></div>
                <div className="col-md-auto index_star"><span className="index_label_text">&nbsp;&nbsp;&nbsp;&nbsp;*</span></div>
              </div>
              <div className="row justify-content-center form">
                <div className="col-md-auto index_label_role">
                  <span className="index_label_text">I am: </span>&nbsp;&nbsp;
                  <input type="radio" name="role" value="PROFESSOR" onChange={ () => this.setState({ role: $('[name="role"]:checked').val() }) } />&nbsp;<span className="index_label_text">Professor</span>&nbsp;&nbsp;
                  <input type="radio" name="role" value="GRADER" onChange={ () => this.setState({ role: $('[name="role"]:checked').val() }) } />&nbsp;<span className="index_label_text">Grader&nbsp;&nbsp;&nbsp;&nbsp;*</span>
                </div>
              </div>
              <div className="row justify-content-center form">
                <div className="col-md-auto">
                  <button type="submit" className="btn btn-primary subbtn" title="Warning!" data-container="body" disabled={ this.enableRegisterButton() }
                    data-toggle="popover" data-placement="right" data-content="Passwords don't match!">Register</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    );
  }

  /**
   * Send the user registration request to the end point.
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
    // data.append('email', this.state.email);
    // data.append('username', this.state.name);
    data.append('username', this.state.email);
    data.append('password', this.state.password);
    data.append('role', this.state.role);
    fetch('/registration', {
      method: 'POST',
      body: data
    });

    // Clear registration cache
    $('#email').val('');
    $('#name').val('');
    $('#pwd').val('');
    $('#crfmpwd').val('');
    $('#email').val('');
    $('[name="role"]:checked').val('');
    this.setState({
      page: 0,
      email: "",
      name: "",
      password: "",
      cfrm: "",
      role: ""
    });
  }

  /**
   * Enabled the register button.
   */
  enableRegisterButton() {
    return !(this.state.email != "" && this.state.name != "" && this.state.password != "" && this.state.cfrm != "" && this.state.role != "");
  }

  /**
   * Enabled the log in button.
   */
  enableLoginButton() {
    return !(this.state.email != "" && this.state.password != "");
  }

  /**
   * Render the user login UI.
   */
  renderLogin() {
    return (
      <div>
        { this.renderNav() }
        <div className="container">
          { this.renderBanner() }
          <div id="indexcontain">
            <div className="row justify-content-center reg_slogan_col">
              <div className="col-md-auto"><span id="reg_slogan">User Login</span></div>
            </div>
            <form onSubmit={ e => this.onLoginSubmit(e) }>
              <div className="row justify-content-center form">
                <div className="col-md-auto index_label"><span className="index_label_text">Email</span></div>
                <div className="col-md-auto"><input type="email" id="email" placeholder="Email" onChange={ () => this.setState({ email: $('#email').val() }) }/></div>
                <div className="col-md-auto index_star"><span className="index_label_text">&nbsp;&nbsp;&nbsp;&nbsp;*</span></div>
              </div>
              <div className="row justify-content-center form">
                <div className="col-md-auto index_label"><span className="index_label_text">Password</span></div>
                <div className="col-md-auto">
                  <input type="password" id="pwd" placeholder="Password" onChange={ () => this.setState({ password: $('#pwd').val() }) }/>
                </div>
                <div className="col-md-auto index_star"><span className="index_label_text">&nbsp;&nbsp;&nbsp;&nbsp;*</span></div>
              </div>
              <div className="row justify-content-center form">
                <div className="col-md-auto index_label_role">
                  <input id="admin" type="checkbox"/>&nbsp;&nbsp;<span className="index_label_text">I am Admin</span>&nbsp;&nbsp;
                </div>
              </div>
              <div className="row justify-content-center form">
                <div className="col-md-auto">
                  <button type="submit" className="btn btn-primary subbtn" title="Warning!" data-container="body" disabled={ this.enableLoginButton() }
                    data-toggle="popover" data-placement="right" data-content="Invalid credential!">Log In</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    );
  }

  /**
   * Send the user login request to the end point.
   */
  onLoginSubmit(e) {
    e.preventDefault();

    // Form the JSON data which needs to be sent
    var that = this;
    var xhr = new XMLHttpRequest();
    var data = JSON.stringify({
      "username": this.state.email,
      "password": this.state.password
    });
    xhr.withCredentials = true;

    // Create a listener for setting cookie 
    xhr.addEventListener("readystatechange", function() {
      if (this.readyState === 4) {
        if (this.status == 200) {
          var datan = null;
          var xhrn = new XMLHttpRequest();
          xhrn.withCredentials = true;
          createCookie('UserName', this.getResponseHeader('User'));
          createCookie('Authorization', this.getResponseHeader('Authorization'));

          // Add another listener for getting response.
          xhrn.addEventListener("readystatechange", function() {
            if (this.readyState === 4) {
              $('[data-toggle="popover"]').popover('hide');
              createCookie('uid', this.responseText);
              if ($('#admin').is(":checked")) {
                that.setState({ page: 4 })
              } else {
                that.setState({ page: 3 })
              }
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
                <h5>Step 1: Add Student (GitHub Repo)<button type="button" className="btn btn-primary add_student" onClick={ () => this.addStudent() }>Add Student</button></h5>
                <div id="students"></div><hr/>
                <h5>Step 2: Define Run<button type="button" className="btn btn-primary advanced" disabled={ this.disableStep2('btn') } onClick={ () => this.advancedSettings() }>Advanced</button></h5>
                  <div className="row">
                    <div className="col-3 step2label">Run ID:</div>
                    <div className="col">
                      <input type="text" id="runid" placeholder={ this.disableStep2('btn') ? "Add some students first" : "Run ID" }
                        disabled={ this.disableStep2('text') } onChange={ () => this.setState({ runID: $('#runid').val() }) }/>
                    </div>
                  </div>
                  <div className="row">
                    <div className="col-3 step2label">Description:</div>
                    <div className="col">
                      <input type="text" id="rundescription" placeholder={ this.disableStep2('btn') ? "Add some students first" : "Run Description" }
                        disabled={ this.disableStep2('text') } onChange={ () => this.setState({ runDescription: $('#rundescription').val() }) }/>
                    </div>
                  </div>
                  <div className="row">
                    <div className="col-3 step2label">Share With:</div>
                    <div className="col">
                      <input type="text" id="sharedusers" placeholder={ this.disableStep2('btn') ? "Add some students first" : "Enter the name, separed by ','" }
                        disabled={ this.disableStep2('text') } onChange={ () => this.setState({ runDescription: $('#rundescription').val() }) }/>
                    </div>
                  </div>
                  <div className="row">
                    <div className="col">
                      <button type="button" className="btn btn-primary runCheck" onClick={ () => this.runCheck() } title="Information" data-container="body" disabled={ this.disableFinalRun() }
                        data-toggle="popover" data-placement="right" data-content="Check started, the window will be closed in 2 secs">
                        { this.disableStep2('btn') ? "Add some students first" : this.disableFinalRun() ? "Add run ID and a description" : "Run" }
                      </button>
                    </div>
                  </div>
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
                <button type="button" className="btn btn-danger" data-dismiss="modal" onClick={ () => this.resetStudent() }>Cancel</button>
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
    var result = this.state.student == 1;
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
    return !(!this.disableStep2('btn') && this.state.runID.length != 0 && this.state.runDescription.length != 0);
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
    $("#slider1").val(50).slider("refresh");
    $("#slider2").val(50).slider("refresh");
    $("#slider3").val(50).slider("refresh");
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
   * Remove a student from the modal.
   */
  removeStudent() { }

  /**
   * Add a new student to the modal.
   */
  addStudent() {
    var stu = this.state.student;
    var newGit = "<div id='stu" + stu.toString() + "'>"
    newGit += "<input type='text' class='git'" + " id='hw" + stu.toString() + "' placeholder='Student " + stu.toString() + " GitHub Link'/> ";
    newGit += "<input type='text' class='nm'" + " id='nm" + stu.toString() + "' placeholder='Student " + stu.toString() + " Name'/> ";
    // newGit += "<button class='removestudent' id='remove" + stu.toString() + "'>&times;</button></div>";
    // $("#remove").attr("click", this.removeStudent());
    newGit += "<button class='removestudent' id='remove'>&times;</button></div>";
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

    // Disable run button and close the window
    $(".runCheck").attr("disabled", true);
    setTimeout(function() {
      $('#myModal').modal('hide');
    }, 2000);
    
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
    data.append("sharedUsers", $("#sharedusers").val());

    var weight = [];
    weight.push($('#slider1').val())
    weight.push($('#slider2').val())
    weight.push($('#slider3').val())
    data.append("strategiesWeight", weight);
    data.append("gitUrls", links);
    data.append("studentNames", names);

    /*
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
    xhr.send(data);*/
    
    var test = ["sample_run"];
    this.setState({
      runs: test
    });
    location.reload;
  }

  /**
   * Show the statistic of a certain run.
   */
  showStatistic(r) {
    
    /*
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
          var row = "<div class='row' id='dropdown'>";
          sim = result.reportFile.comparisonList[i].scores.totalScore;
          row += "<div class='btn-group'>";
          row += "<button type='button' class='btn btn-default dropdown-toggle' data-toggle='dropdown'>";
          row += result.reportFile.comparisonList[i].filename1 + " vs. " + result.reportFile.comparisonList[i].filename2 + "<span class='caret'></span>"
          row += "</button><ul class='dropdown-menu'>";
          row += "<div id='chart1'></div>";
          row += "</ul></div></div>";
          showing += row;
        }

        $("#sa").html(showing);
			}
		});
		xhr.open("GET", endPoint);
		xhr.setRequestHeader("Cache-Control", "no-cache");
		xhr.setRequestHeader("Authorization", document.cookie);
    xhr.send(data);*/


    var result = new Object();

    
    result.data = [];
    var res1 = new Object();
    res1.student1 = "Student 01";
    res1.student2 = "Student 02";
    res1.file1 = "File 01";
    res1.file2 = "File 02";
    res1.percentage = 0.7;
    res1.severity = "Medium";
    res1.gitDiff = "TBD";
    result.data.push(res1);

    var res2 = new Object();
    res2.student1 = "Student 03";
    res2.student2 = "Student 04";
    res2.file1 = "File 03";
    res2.file2 = "File 04";
    res2.percentage = 0.9;
    res2.severity = "High";
    res2.gitDiff = "TBD";
    result.data.push(res2);

    result.description = "Sample Description";
    result.time = "2018-04-01 00:00:00"
    result.createdUser = "Junhao";


    var finalShowing = "<div class='row'><p>Run Description: " + result.description + "</p></div>";
    finalShowing += "<div class='row'><p>Created Time: " + result.time + "</p></div>";
    finalShowing += "<div class='row'><p>Created By: " + result.createdUser + "</p></div>";


    finalShowing += "<table class='table'><thead><tr>" + 
      "<th>Student1</th><th>Student2</th><th>File1</th><th>File2</th><th>Percentage</th><th>Severity</th><th>GitDiff</th>" +
      "</tr></thead><tbody>";

    for (var i = 0; i < result.data.length; i++) {
      var row = "<tr>";
      row += "<td>" + result.data[i].student1 + "</td>";
      row += "<td>" + result.data[i].student2 + "</td>";
      row += "<td>" + result.data[i].file1 + "</td>";
      row += "<td>" + result.data[i].file2 + "</td>";
      row += "<td>" + result.data[i].percentage + "</td>";
      row += "<td id='" + result.data[i].severity + "'>" + result.data[i].severity + "</td>";


      /**
       

  
    
    Sample GitDiff Data</div></div></td>


       */
      // row += "<td>" + result.data[i].gitDiff + "</td>";

      row += "<td><div class='dropdown'>" +
        "<button class='btn btn-primary dropdown-toggle' type='button' id='dropdownMenuButton' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>" +
        "View</button><div class='dropdown-menu dropdown-menu-right' id='gitdiffdata' aria-labelledby='dropdownMenuButton'>" +
        "Sample GitDiff Data</div></div></td>";


      row += "</tr>"
      finalShowing += row;
    }

    finalShowing += "</tbody></table>";
    $("#title").text("Statistics of the " + r);
    $("#sa").html(finalShowing);


  }

  /**
   * Render the main UI.
   */
  render() {
    if (readCookie('UserName') != null && readCookie('UserName') != "" && this.state.page != 4) {
      return(this.renderPlagiarismCheck());
    } else if (this.state.page == 4) {
      return(
        <div>{ this.renderNav() }<Admin /></div>
      );
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
