class Application extends React.Component {
  constructor() {
    super();
    this.state = {
      page: 0,
      runs: [],
      statistic: -1,
      student: 1
    }
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
        <div className="container pcheck">
          <div className="row justify-content-md-center">
            <div className="col-3 runs">{ this.renderSideColumn() }</div>
            <div className="col statistics">
              <h4>{ this.state.runs.length == 0 ? "Cuurrently there are no runs." : "Select a run and check." }</h4>
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
                <input type="text" id="rundescription" placeholder="Run Description"/>
                <div id="students"></div>
                <input type="text" id="sharedusers" placeholder="Shared Users"/>
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-primary" onClick={ () => this.runCheck() } title="Information" data-container="body"
                  data-toggle="popover" data-placement="right" data-content="Check started, close the window">Run Check</button>
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
   */
  renderSideColumn() {
    return(
      <div className="container">
        <div className="row">
          <div className="col sider">
            <button type="button" className="btn btn-danger btn-lg" data-toggle="modal" data-target="#myModal">Add New Run</button>

            <button type="button" className="btn btn-primary" onClick={ () => this.showStatistic(0) }>Run 0</button>
          </div>
        </div>
      </div>
    );
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
		xhr.withCredentials = true;
		xhr.addEventListener("readystatechange", function () {
			if (this.readyState === 4) {
        var text = this.responseText;        
        $('[data-toggle="popover"]').popover('show'); 
        setTimeout(function() {
          $('[data-toggle="popover"]').popover('hide'); 
        }, 2000);
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
        console.log(result);
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
    /*
    if (this.state.page == 1 && readCookie('UserName') == null) {
      return(this.renderRegistration());
    } else if (this.state.page == 0 && readCookie('UserName') == null) {
      return(this.renderLogin());
    } else {
      
    }*/

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