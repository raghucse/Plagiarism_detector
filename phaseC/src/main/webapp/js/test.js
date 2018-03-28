class Application extends React.Component {
  constructor() {
    super();
    this.state = {
      page: 0,
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
    if (document.getElementById("pwd").value != document.getElementById("crfmpwd").value ||
        document.getElementById("pwd").value == "" || document.getElementById("crfmpwd").value == "") {
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
    data.append('username', document.getElementById("email").value);
    data.append('password', document.getElementById("pwd").value);
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
      "username": document.getElementById("email").value,
      "password": document.getElementById("pwd").value
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
      <div>Log in already.</div>
    );
  }

  /**
   * Render the main UI.
   */
  render() {
    if (this.state.page == 1) {
      return(this.renderRegistration());
    } else if (this.state.page == 0) {
      return(this.renderLogin());
    } else {
      return(this.renderPlagiarismCheck());
    }
  }
}

ReactDOM.render(
	<Application />,
	document.getElementById('root')
);