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
    if (document.getElementById("pwd").value != document.getElementById("crfmpwd").value ||
        document.getElementById("pwd").value == "" || document.getElementById("crfmpwd").value == "") {
      $('[data-toggle="popover"]').popover('show'); 
      setTimeout(function() {
        $('[data-toggle="popover"]').popover('hide'); 
      }, 2000);
      return;
    }

    $('[data-toggle="popover"]').popover('hide');
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

    console.log(document.getElementById("email").value);
    console.log(document.getElementById("pwd").value);
    
    
    var data = JSON.stringify({
      "username": document.getElementById("email").value,
      "password": document.getElementById("pwd").value
    });

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function () {
      if (this.readyState === 4) {
        if(this.status == 200) {
          createCookie('UserName',this.getResponseHeader('User'));
          createCookie('Authorization',this.getResponseHeader('Authorization'));
          var datan = null;
          var xhrn = new XMLHttpRequest();
          xhrn.withCredentials = true;
          xhrn.addEventListener("readystatechange", function () {
            if (this.readyState === 4) {
              $('[data-toggle="popover"]').popover('hide');
              createCookie('uid',this.responseText);
              this.setState({ page: 3 });
            }
          });
          xhrn.open("GET", "/user?userName="+readCookie('UserName'));
          xhrn.setRequestHeader("Authorization", readCookie('Authorization'));
          xhrn.setRequestHeader("Cache-Control", "no-cache");
          xhrn.send(datan);
        } else {
          $('[data-toggle="popover"]').popover('show'); 
          setTimeout(function() {
            $('[data-toggle="popover"]').popover('hide'); 
          }, 2000);
        }
      }
    });
  }

  /**
   * Render the plagiarism check UI.
   */
  renderPlagiarismCheck() {
    return(
      <div>sa</div>
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