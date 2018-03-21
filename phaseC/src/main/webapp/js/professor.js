class Professor extends React.Component {

	/** 
	 * Constructor
	 * 
	 * The state contains:
	 *   page: to show the assignment page or the class information page
	 *   assignments: name of all assignments in this class
	 *   statistics: which assignment to show the statistics
	 * 
	 * Before constructing the state,
	 *   the backend shall return an array listing all assignments' names.
	 * 
	*/
	constructor() {
		super();
		this.state = {
			page: 0,
			assignments: ["0", "1", "2"],
			statistics: 0
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
						<button id="assignmentBanner" className={ this.state.page == 0 ? "currentpage" : "futurepage" }
						  onClick={ () => this.setState({page: 0}) } >Assignment</button>
					</div>
					<div className="col-md-2">
						<button id="classinfo" className={ this.state.page == 1 ? "currentpage" : "futurepage" }
						  onClick={ () => this.setState({page: 1}) } >Class Information</button>
					</div>
					<div className="col">
						<button id="logout">Log out</button>
					</div>
				</div>
			</div>
		);
	}

	// Show all assignments
	// TODO: change the way to judge whether it is the current assignment
	showAssignments() {
		const assiElements = [];
		for (let assi of this.state.assignments) {
			assiElements.push(
				<div className="row">
					<div className="col">
						<button className={ this.state.statistics == Number(assi) ? "currentAssignment" : "futureAssignment" }
						  onClick={ () => this.setState({ statistics: Number(assi) }) }>
							Assignment { assi }</button>
						</div>
				</div>
			);
		}

		return(
			<div className="container" id="assignmentsWrapper">
				<div className="row">
					<div className="col">
						<button className="addAssignment" onClick={ () => this.addAssignments() }>Add Assignment</button>
					</div>
				</div>
				{ assiElements }
			</div>
		);
	}

	// Add assignments
	// TODO: will be replaced, current code is for testing use only
	addAssignments() {
		let test = this.state.assignments;
		test.push("X");
		this.setState({
			assignments: test
		});
	}

	// Compare two files
	onCompareSubmit(e) {
		/*
		e.preventDefault();
		var data = new FormData();
		data.append('file1', document.getElementById('file1').files[0]);
		data.append('file2', document.getElementById('file2').files[0]);
		
		var xhr = new XMLHttpRequest();
		xhr.withCredentials = true;

		xhr.addEventListener("readystatechange", function () {
			if (this.readyState === 4) {
				if(this.responseText == "TODO")
					console.log("todo");
				else
					console.log("todo");
				}
		});
			
		xhr.open("POST", "TODO");
		xhr.setRequestHeader("Cache-Control", "no-cache");
		xhr.send(data);
		*/
	}

	// Rend	er the UI
	render() {
		if (this.state.page == 0) {
			return(
				<div className="container">
					<div className="row">
						{ this.showBanner() }
					</div>
					<div className="row">
						<div className="col">
							{ this.showAssignments() }
						</div>
						<div className="col-5" >
							<div className="container" id="statistics">
								<div className="row">
									<div className="col">The statistics of the latest run</div>
									<div className="w-100"></div>
									<div className="col">
									<form onSubmit={ e => this.onCompareSubmit(e) }>
											<table>
												<tr>
													<td><span>File 1</span></td>
													<td><input type="file" id="file1"/></td>
												</tr>
												<tr>
													<td><span>File 2</span></td>
													<td><input type="file" id="file2"/></td>
												</tr>
												<input className="button" type="submit" value="Submit"/>
												<div id="result"></div>
											</table>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			);
		} else {
			return(
				<div className="container">
					<div className="row">
						{ this.showBanner() }
					</div>
					<div className="row" id="statistics">
						Class Information!
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