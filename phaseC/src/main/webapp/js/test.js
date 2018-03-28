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
    if (document.getElementById("pwd").innerText != document.getElementById("crfmpwd").innerText ||
        document.getElementById("pwd").value == "" || document.getElementById("crfmpwd").value == "") {
      $('[data-toggle="popover"]').popover('show'); 
      setTimeout(function() {
        $('[data-toggle="popover"]').popover('hide'); 
      }, 2000);
      return;
    } else {
      $('[data-toggle="popover"]').popover('hide');
      return;
    }
  }

  /**
   * Render the login UI.
   */
  renderLogin() {
    return (
      <div className="container">
        { this.renderBanner() }
        <div id="indexcontain">
          <form onSubmit={ e => this.onLogInSubmit(e) }>
            <div className="row justify-content-center form">
              <div className="col-md-auto"><input type="email" id="email" placeholder="Email"/></div>
            </div>
            <div className="row justify-content-center form">
              <div className="col-md-auto"><input type="password" id="pwd" placeholder="Password"/></div>
            </div>
            <div className="row justify-content-center form">
              <div className="col-md-auto">
                <button type="submit" className="btn btn-primary subbtn">Submit</button>
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

  }

  /**
   * Render the plagiarism check UI.
   */
  renderPlagiarismCheck() {

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