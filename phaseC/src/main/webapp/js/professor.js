class Professor extends React.Component {

	/** 
	 * Constructor
	 * 
	 * The state contains:
	 *   page: to show the assignment page or the class information page
	*/
	constructor() {
		super();
		this.state = {
			page: 0
		}
	}

	// Show the banner UI
	showBanner() {
		return(
			<div className="container" id="banner">
				<div className="row">
					<div className="col-md-2">
						<select className="selectpicker">
							<option>CS5500</option>
							<option>TBD</option>
						</select>
					</div>
					<div className="col-md-2">
						<button id="assi" className={ this.state.page == 0 ? "currentpage" : "futurepage" } onClick={ () => this.setState({page: 0}) } >Assignment</button>
					</div>
					<div className="col-md-2">
						<button id="classinfo" className={ this.state.page == 1 ? "currentpage" : "futurepage" } onClick={ () => this.setState({page: 1}) } >Class Information</button>
					</div>
					<div className="col">
						<button id="logout">Log out</button>
					</div>
				</div>
			</div>
		);
	}

	// Rend	er the UI
	render() {
		if (this.state.page == 0) {
			return(
				<div className="container">
					<div className="row">
						{ this.showBanner() }
					</div>
				</div>
			);
		} else {
			return(
				<div className="container">
					<div className="row">
						{ this.showBanner() }
					</div>
				</div>
			);
		}
	}
}

ReactDOM.render(
	<Professor />,
	document.getElementById('root')
);