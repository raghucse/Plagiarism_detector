class Professor extends React.Component {

	/** 
	 * Constructor
	 * 
	 * The state contains:
	 *   page: 0 for the assignment page and 1 for class information page
	 *   assignments: list of all assignments' ID in this class
	 *   students: list of all students' ID in this class
	 *   statistics: ID of the selected assignment to check 
	*/
	constructor() {
		super();

		/**
		 * TODO: get a course object/json from the end point and initialize the following state
		 */
		var loadAssignments = [0, 1, 2];
		var loadStudents = [0, 1, 2, 3, 4, 5];

		this.state = {
			page: 0,
			assignments: loadAssignments,
			students: loadStudents,
			statistics: 0
		}
	}

	/**
	 * Show the banner UI
	 */
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

	/**
	 * Show all assignments
	 */
	showAssignments() {
		const assiElements = [];
		for (let assi of this.state.assignments) {
			assiElements.push(
				<div className="row">
					<div className="col">
						<button className={ this.state.statistics == assi ? "currentAssignment" : "futureAssignment" }
						  onClick={ () => this.setState({ statistics: assi }) }>Assignment { assi }</button>
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

	/**
	 * Add assignments
	 * 
	 * TODO: will be replaced, current code is for testing use only
	 */
	addAssignments() {
		let test = this.state.assignments;
		test.push("9");
		this.setState({
			assignments: test
		});
	}

	/**
	 * Compare two files, for test use only
	 */
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

	/**
	 * Modal animations for editing GitHub link in a certain assignment
	 */
	editAllSubmissions() {
		var modal = document.getElementById('myModal');
		var span = document.getElementsByClassName("close")[0];
		span.onclick = function() {
			modal.style.display = "none";
		}
		modal.style.display = "block";
	}

	// update students' GitHub link
	updateGitHubLink() {
		/**
		 * TODO: post the query to DB to update students' GitHub link
		 */
	}

	/**
	 * Render the main UI
	 */
	render() {
		if (this.state.page == 0) {
			return(
				<div className="container">
					<div className="row">{ this.showBanner() }</div>
					<div className="row">
						<div className="col">{ this.showAssignments() }</div>
						<div className="col-5" >
							<div className="container" id="statistics">
								<div className="row">
									<div className="col">
									<span>For testing use:</span><br/>
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
									<div className="col">
										<button onClick={this.editAllSubmissions.bind(this)} id="editAllSubmissions">Edit all submissions</button>
										<div id="myModal" className="modal">
											<div className="modal-content">
											  <span className="close">&times;</span>
												<div className="student_container">
													<div className="row">
														<div className="col-md-4">Student 1</div>
														<div className="col-md-auto"><input type="text" id=""></input></div>
													</div>
													<div className="row">
														<div className="col-md-4">Student 2</div>
														<div className="col-md-auto"><input type="text" id=""></input></div>
													</div>
													<div className="row">
														<div className="col-md-4">Student 3</div>
														<div className="col-md-auto"><input type="text" id=""></input></div>
													</div>
													<div className="row">
														<div className="col">
															<button id="" onClick={ this.updateGitHubLink.bind(this) }>Save</button>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div className="col">
										<button id="runCheck">Run</button>
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