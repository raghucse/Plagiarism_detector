class Index extends React.Component {

	/** 
	 * Constructor
	 * 
	 * The state contains:
	 *   page: to show the log_in page or the register page
	 *   email: the user's email address
	 *   password: the user's password
	 *   cfrm_pwd: confirm the password for register
	 *   role: which role is the user
	*/
	constructor() {
		super();
		this.state = {
			page: 0,
			email: '',
			password: '',
			cfrm_pwd: '',
			role: 'PROFESSOR'
		}
	}

	/**
   * Make a toast
   * http://blog.csdn.net/yuetingzhuying/article/details/56489439
   */
  toast(msg) {
    setTimeout(function() {
        document.getElementsByClassName('toast-wrap')[0].getElementsByClassName('toast-msg')[0].innerHTML = msg;
        var toastTag = document.getElementsByClassName('toast-wrap')[0];
        toastTag.className = toastTag.className.replace('toastAnimate', '');
        setTimeout(function() {
          toastTag.className = toastTag.className + ' toastAnimate';
        }, 100);
      }, 100);
	}
	
	// Submit the form to login
	// ec2-34-210-26-119.us-west-2.compute.amazonaws.com
	onLogInSubmit(e) {
		e.preventDefault();
        var data = JSON.stringify({
            "username": this.state.email,
            "password": this.state.password
        });

        var xhr = new XMLHttpRequest();
        xhr.withCredentials = true;

        xhr.addEventListener("readystatechange", function () {
        	console.log(this)
            if (this.readyState === 4) {
                if(this.status == 200){
                    createCookie('UserName',this.getResponseHeader('User'));
                    createCookie('Authorization',this.getResponseHeader('Authorization'));
                    var datan = null;
                    var xhrn = new XMLHttpRequest();
                    xhrn.withCredentials = true;
                    xhrn.addEventListener("readystatechange", function () {
                        if (this.readyState === 4) {
                            createCookie('uid',this.responseText);
                            window.location.replace('http://localhost:8080/home.html');
                        }
                    });

                    xhrn.open("GET", "/user?userName="+readCookie('UserName'));
                    xhrn.setRequestHeader("Authorization", readCookie('Authorization'));
                    xhrn.setRequestHeader("Cache-Control", "no-cache");
                    xhrn.send(datan);
                    //window.location.replace('http://localhost:8080/home.html');
                }
                else
                    setTimeout(function() {
                        document.getElementsByClassName('toast-wrap')[0].getElementsByClassName('toast-msg')[0].innerHTML = 'INVALID CREDENTIALS';
                        var toastTag = document.getElementsByClassName('toast-wrap')[0];
                        toastTag.className = toastTag.className.replace('toastAnimate', '');
                        setTimeout(function() {
                            toastTag.className = toastTag.className + ' toastAnimate';
                        }, 100);
                    }, 100);
            }
        });

        xhr.open("POST", "/login");
        xhr.setRequestHeader("Cache-Control", "no-cache");
        xhr.send(data);
	}

	// Submit the form to register
	onRegisterSubmit(e) {
		e.preventDefault();

		if(this.state.cfrm_pwd != this.state.password) {
			this.toast('Passwords must be same!');
			return;
		}

		/**
		 * Send the data to the registration end point.
		 * 
		 * Note: don't send JSON objects, instead, send FormData
		*/
		var data = new FormData();
		data.append('username', this.state.email);
		data.append('password', this.state.password);
		data.append('role', this.state.role);

		fetch('http://localhost:8080/registration', {
			method: 'POST',
			body: data
		});
		window.location.replace('http://localhost:8080/index.html');
	}
	
	// Rend	er the UI
	render() {
		if (this.state.page == 0) {
			return(
				<div>
					<p>
						<button className={ this.state.page == 1 ? "clickedButton" : "unclickedButton" }
							onClick={ () => this.setState({ page: 1 }) }>Register</button>
						<button className={ this.state.page == 0 ? "clickedButton" : "unclickedButton" }
							onClick={ () => this.setState({ page: 0 }) }>Log In</button>&nbsp;<br/>
					</p>
					<br/><div>
					<table>
					<form onSubmit={ e => this.onLogInSubmit(e) }>
						<tr>
							<td><span>Email</span></td>
							<td><input type="text" onChange={ ev => this.setState({ email: ev.target.value }) } /></td>
						</tr>
						<tr>
							<td><span>Password</span></td>
							<td><input type="password" onChange={ ev => this.setState({ password: ev.target.value }) } /></td>
						</tr>
						<tr>
						  <td><a href="./mock/Home.html">View UI as non-wireframes</a></td>
							<td><input className="button" type="submit" value="Submit"/></td>
						</tr>
					</form>
					</table></div>
					<div className="toast-wrap">
          	<span className="toast-msg"></span>
        	</div>
				</div>
			);
		} else {
			return(
				<div>
					<p>
						<button className={ this.state.page == 1 ? "clickedButton" : "unclickedButton" }
							onClick={ () => this.setState({ page: 1 }) }>Register</button>
						<button className={ this.state.page == 0 ? "clickedButton" : "unclickedButton" }
							onClick={ () => this.setState({ page: 0 }) }>Log In</button>&nbsp;<br/>
					</p>
					<br /><div>
					<table id="temp">
						<form onSubmit={ e => this.onRegisterSubmit(e) }>
						<tr>
							<td><span>Email</span></td>
							<td><input type="email" onChange={ ev => this.setState({ email: ev.target.value }) } /></td>
          	</tr>
						<tr>
							<td><span>Password</span></td>
            	<td><input type="password" onChange={ ev => this.setState({ password: ev.target.value }) } /></td>
          	</tr>
						<tr>
            	<td><span>Confirm Password</span></td>
							<td><input type="password" onChange={ ev => this.setState({ cfrm_pwd: ev.target.value }) } /></td>
          	</tr>
          	<tr>
            	<td><span>Role</span></td>
            	<td>
              	<select onChange={ ev => this.setState({ role: ev.target.value }) }>
                	<option value ="PROFESSOR">Professor</option>
                	<option value ="GRADER">Grader</option>
                	<option value ="ADMINISTRATOR">Administrator</option>
              	</select>
            	</td>
          	</tr>
          	<tr>
            	<td><input className="button" type="submit" value="Submit"/></td>
						</tr>
					</form>
        	</table></div>
					<div className="toast-wrap">
          	<span className="toast-msg"></span>
        	</div>
				</div>
			);
		}
	}
}

ReactDOM.render(
	<Index />,
	document.getElementById('root')
);
