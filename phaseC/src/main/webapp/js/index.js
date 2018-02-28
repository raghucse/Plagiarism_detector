class Test extends React.Component {
	constructor() {
		super();
		this.state = { page: 0 }
	}

    render() {
    	if (this.state.page == 0) {
    		return(
    			<div>
                <button onClick={ () => this.setState({ page: 0 })}>Log In</button>
                <button onClick={ () => this.setState({ page: 1 })}>Register</button>
				<table>
					<tr>
						<td>Email</td>
						<td><input type="text"/></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="password"/></td>
					</tr>
					<tr>
						<td>
						<input class="button" type="submit" value="Submit"/>
						<input class="button" type="reset" value="Reset" />
						</td>
					</tr>
					<tr>
       				    <td colspan="2" align="center">
              			<a href="#"><br />Forget password?</a>
            			</td>
          			</tr>
				</table>
				</div>
			);
		} else {	
			return(
				<div>
				<button onClick={ () => this.setState({ page: 0 })}>Log In</button>
				<button onClick={ () => this.setState({ page: 1 })}>Register</button>
				<table>
          			<tr>
            			<td>Email</td>
            			<td><input type="email" /></td>
          			</tr>
          			<tr>
            			<td>Password</td>
            			<td><input type="password" /></td>
          			</tr>
          			<tr>
            			<td>Confirm Password</td>
            			<td><input type="password" /></td>
          			</tr>
          			<tr>
            			<td>Role</td>
            			<td>
              				<select>
                				<option value ="Professor">Professor</option>
                				<option value ="Grader">Grader</option>
                				<option value ="Admin">Administrator</option>
              				</select>
            			</td>
          			</tr>
          			<tr>
            			<td colspan="2" align="center">
              				<br /><input class="button" type="submit" value="Submit" />
                  			<input class="button" type="reset" value="Reset" />
            			</td>
          			</tr>
        			</table>
				</div>
			);
		}
	}
}

ReactDOM.render(
	<Test />,
	document.getElementById('root')
);