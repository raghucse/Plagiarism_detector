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
					<div className="col-md-2" id={ this.state.page == 0 ? "currentpage" : "futurepage" }>Assignment</div>
					<div className="col-md-2" id={ this.state.page == 1 ? "currentpage" : "futurepage" }>Class Information</div>
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
				<div>
					d
				</div>
			);
		}
	}
}

ReactDOM.render(
	<Professor />,
	document.getElementById('root')
);