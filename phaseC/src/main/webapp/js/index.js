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
			role: 'Professor'
		}
	}

	// Submit the form to login
	onLogInSubmit() {
		var _data = new FormData();
		_data.append('username', this.state.email);
		_data.append('password', this.state.password);

		alert("Start to login");

		fetch('http://localhost:8080/login', {
			method: 'POST',
			body: _data
		}).then((msg) => {
			alert("Log in successfull!");
			window.location.replace("http://localhost:8080/home.html");
		}).catch((error) => {
			alert(error);
		});
	}

	// Submit the form to register
	onRegisterSubmit() {
		
		if(this.state.cfrm_pwd != this.state.password) {
			alert('Password must be same!');
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
		alert("Registration succeed!");
	}
	
	// Render the UI
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
					<form onSubmit={ () => this.onLogInSubmit() }>
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
							<td><input className="button" type="reset" value="Reset" /></td>
						</tr>
						<tr>
							<td><a href="#">Forget password?</a></td>
							<td><a href="../home.html">View UI as non-wireframes</a></td>
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
						<form onSubmit={ () => this.onRegisterSubmit() }>
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
                	<option value ="Professor">Professor</option>
                	<option value ="Grader">Grader</option>
                	<option value ="Administrator">Administrator</option>
              	</select>
            	</td>
          	</tr>
          	<tr>
            	<td><input className="button" type="submit" value="Submit"/></td>
              <td><input className="button" type="reset" value="Reset" /></td>
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