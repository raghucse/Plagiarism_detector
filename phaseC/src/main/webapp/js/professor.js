class Professor extends React.Component {

	/** 
	 * Constructor
	 * 
	 * The state contains:
	 *   page: to show the assignment page or the class information page
	 *   email: the user's email address
	 *   password: the user's password
	 *   cfrm_pwd: confirm the password for register
	 *   role: which role is the user
	*/
	constructor() {
		super();
		this.state = {
			page: 0
		}
	}

	// Rend	er the UI
	render() {
		if (this.state.page == 0) {
			return(
				<div>
					sa
				</div>
			);
		} else {
			return(
				<div>
				
				</div>
			);
		}
	}
}

ReactDOM.render(
	<Professor />,
	document.getElementById('root')
);