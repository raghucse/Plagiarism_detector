class Application extends React.Component {
  constructor() {
    super();
    this.state = {
      page: 0,
      email: "",
      name: "",
      password: "",
      cfrm: "",
      role: "",
      message: ""
    }
  }

  /**
   * Render the navigation bar UI.
   */
  renderNav() {
    return(
      <div>
        <div className="row align-items-center top">
          <div className="col-md-auto"></div>
          <div className="col ">Plagiarism Checker</div>
          <div className="col"></div>
          <div className="col"></div>
          <div className="col-md-auto">{ readCookie('UserName') == null ? "" : "Welcome " + this.truncateUserName(readCookie('UserName')) }</div>
          <div className="col-md-auto">
            { readCookie('UserName') == null ? "" :
              <button type="button" className="btn btn-danger logout" onClick={ () => this.logout() }>Log out</button> }
          </div>
          <div className="col-md-auto"></div>
        </div>
      </div>
    );
  }

  /**
   * Get the user name out of the email
   */
  truncateUserName(email) {
    var res = "";
    for (var i = 0; i < email.length; i++) {
      if (email.charAt(i) != '@') {
        res += email.charAt(i);
      } else {
        break;
      }
    }
    return res;
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
                <div className="col-md-auto"><input type="password" id="pwd" placeholder="6 to 15 characters" onChange={ () => this.setState({ password: $('#pwd').val() }) }/></div>
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
                    data-toggle="popover" data-placement="right" data-content="Passwords don't match!">{ !this.enableRegisterButton() ? "Register" : "Please fill in the form"}</button>
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

    // Check password length validity
    if ($("#pwd").val().length < 6 || $("#pwd").val().length > 15) {
      $('[data-toggle="popover"]').attr('data-content', 'Invalid password length!')
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
              <br/>
              <div className="row justify-content-center form">
                <div className="col-md-auto">
                  <button type="submit" className="btn btn-primary subbtn" title="Warning!" data-container="body" disabled={ this.enableLoginButton() }
                    data-toggle="popover" data-placement="right" data-content="Invalid credential!">{ !this.enableLoginButton() ? "Log In" : "Please fill in the form"}</button>
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
              var result = JSON.parse(this.responseText);
              console.log(this.responseText)
              createCookie('uid', result.uid);
              console.log('uid', result.uid);
              
              if (result.role == 'ADMIN') {
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
   * Log out.
   */
  logout() {
    eraseCookie('UserName');
    eraseCookie('Authorization');
    eraseCookie('uid');
    this.setState({ page: 0 });
  }

  /**
   * Render the main UI.
   */
  render() {
    if (readCookie('UserName') != null && readCookie('UserName') != "" && this.state.page != 4) {
      return(
        <div>{ this.renderNav() }<Dashboard /></div>
      );
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
