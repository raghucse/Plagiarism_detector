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

	// Submit the form to login
	onLogInSubmit(e) {
		e.preventDefault();
        var data = new FormData();
        data.append("username", this.state.email);
        data.append("password", this.state.password);

        var xhr = new XMLHttpRequest();
        xhr.withCredentials = true;
		//alert(data)
        xhr.addEventListener("readystatechange", function () {
            if (this.readyState === 4) {
                if(this.responseText == "{\"msg\":\"login successful\"}")
                	window.location.replace('http://ec2-34-210-26-119.us-west-2.compute.amazonaws.com:8080/home.html');
                else
                	console.log("LOGIN FAILED INVALID CREDENTIALS");
            }
        });

        xhr.open("POST", "http://ec2-34-210-26-119.us-west-2.compute.amazonaws.com:8080/login");
        xhr.setRequestHeader("Cache-Control", "no-cache");
        xhr.send(data);
	}

	// Submit the form to register
	onRegisterSubmit(e) {
		e.preventDefault();

		if(this.state.cfrm_pwd != this.state.password) {
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

		fetch('http://ec2-34-210-26-119.us-west-2.compute.amazonaws.com:8080/registration', {
			method: 'POST',
			body: data
		});
        window.location.replace('http://ec2-34-210-26-119.us-west-2.compute.amazonaws.com:8080/index.html');
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
							<td><input className="button" type="submit" value="Submit"/></td>
						</tr>
						<tr>
							<td><a href="#">Forget password?</a></td>
							<td><a href="./mock/Home.html">View UI as non-wireframes</a></td>
          	</tr>
					</form>
					</table>
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
					<table>
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
        	</table>
				</div>
			);
		}
	}
}

ReactDOM.render(
	<Index />,
	document.getElementById('root')
);