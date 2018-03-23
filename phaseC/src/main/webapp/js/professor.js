class Check extends React.Component {

	/**
	 * Constructor
	 */
	constructor() {
		super();

		// Get all the report ID
		// REAL: var url = "/report/userId/"+readCookie('User');
		var data = null;
		var loadRuns = [0, 1, 2];
		var url = "/report/userId/0"
		var xhr = new XMLHttpRequest();
		xhr.withCredentials = true;
		xhr.addEventListener("readystatechange", function () {
			if (this.readyState === 4) {
				console.log(this.responseText);
				loadRuns = [0, 1, 2];
			}
		});		
		xhr.open("GET", url);
		xhr.setRequestHeader("Cache-Control", "no-cache");
		xhr.setRequestHeader("Authorization", readCookie('Authorization'));
		xhr.send(data);

		/**
		 * SIMULATION to get the first report in the list
		 */
		var smallObj = new Object();
		smallObj.file1 = "name1";
		smallObj.file2 = "name2";
		smallObj.percentage = 0.1;

		var obj = new Object();
		obj.reportID = 0;
		var testDiffData = [];
		for (var i = 0; i < 3; i++) {
			testDiffData.push(smallObj);
		}
		obj.diffData = testDiffData;
		// -----------------------------------------------

		// Set up the components state
		this.state = {
			runs: loadRuns,						// a list of all report IDs
			statistics: loadRuns[0],	// by default the page loads the first report
			report: obj,							// by default the page loads the first report
			stuNum: 1,								// by default there is 1 student to add solution	
			userID: 0									// user ID in the session
		}
	}

	/**
	 * Start a new run by popping up a modal,
	 *   which allows the user to add multiple solutions.
	 */
	addRuns() {
		var modal = document.getElementById('myModal');
		var span = document.getElementsByClassName("close")[0];
		span.onclick = function() {
			modal.style.display = "none";
		}
		modal.style.display = "block";
	}

	/**
	 * Show all runs by going through the list of run ID.
	 */
	showRuns() {
		const runElements = [];
		for (let r of this.state.runs) {
			runElements.push(
				<div className="row">
					<div className="col">
						<button className={ this.state.statistics == r ? "currentRun" : "futureRun" }
						  onClick={ () => this.showStatistics(r) }>Run { r }</button>
						</div>
				</div>
			);
		}

		return(
			<div className="container" id="runsWrapper">
				<div className="row">
					<div className="col">
						<button className="addRun" onClick={ () => this.addRuns() }>Add Run</button>
					</div>
				</div>
				{ runElements }
			</div>
		);
	}

	/**
	 * Show statistics of a certain run,
	 *   triggered by pressing the button of a historical run.
	 */
	showStatistics(r) {
		
		// Set state for rendering UI
		this.setState({ statistics: r });

		// Post data
		var data = null;
		var endPoint = "http://localhost:8080/report/reportId/" + r.toString();
		var xhr = new XMLHttpRequest();
		xhr.withCredentials = true;
		xhr.addEventListener("readystatechange", function () {
			if (this.readyState === 4) {
				// this.setState({report: this.responseText});
			}
		});
		xhr.open("GET", endPoint);
		xhr.setRequestHeader("Cache-Control", "no-cache");
		xhr.setRequestHeader("Authorization", document.cookie);
		xhr.send(data);

		var element = document.getElementById("tabledata");

		for (var i = 0; i < this.state.report.diffData.length; i++) {
			var test = document.createElement("tr");
			
			var para = document.createElement("td");
			var node = document.createTextNode(this.state.report.diffData[i].file1);
			para.appendChild(node);
			test.appendChild(para);

			para = document.createElement("td");
			node = document.createTextNode(this.state.report.diffData[i].file2);
			para.appendChild(node);
			test.appendChild(para);

			para = document.createElement("td");
			node = document.createTextNode(this.state.report.diffData[i].percentage);
			para.appendChild(node);
			test.appendChild(para);

		  // element.appendChild(test);
			element.innerHTML = test.innerHTML;
		}
	}

	/**
	 * Add student in the modal
	 */
	addStudent() {
		let newStuNum = this.state.stuNum + 1;
		this.setState({
			stuNum: newStuNum
		});

		var newStudent = '<div className="row"><div className="col-md-5">Student '
			+ this.state.stuNum
			+ '</div><div className="col-md-5"><input type="text" id="'
			+ this.state.stuNum.toString()
			+ '"/></div></div>';
		document.getElementById('github_links').innerHTML += newStudent;
	}

	/**
	 * Run the checks
	 */
	runCheck() {
		// Push all git links to an array
		var links = [];
		for (var i = 1; i < this.state.stuNum; i++) {
			links.push(document.getElementById(i.toString()).value);
		}

		/**
		 * Generate a form data
		 *   runDescription: String
		 *   gitHubLinks: String array
		 *   sharedUsers: String array
		 */
		var data = new FormData();
		data.append("runDescription", document.getElementById('run_description').value);
		data.append("gitHubLinks", links);
		data.append("sharedUsers", document.getElementById('shared_users').value.split(","));
		
		// Post to end point
		var xhr = new XMLHttpRequest();
		xhr.withCredentials = true;
		xhr.addEventListener("readystatechange", function () {
			if (this.readyState === 4) {

				// Here the end point should return a report ID
				console.log(this.responseText);
				var tmp = this.state.runs;
				tmp.push(9);
				this.setState({ runs: tmp });
			}
		});

		xhr.open("POST", "END POINT");
		xhr.setRequestHeader("Cache-Control", "no-cache");
		xhr.setRequestHeader("Authorization", document.cookie);
		xhr.send(data);
	}

	/**
	 * Render the main UI.
	 */
	render() {
		return(
			<div className="container">
				<div className="row">
					<div className="col-1">{ this.showRuns() }</div>
					<div className="col-4" id="statistics">
						<div className="container report">
							<div className="row">
								<div className="col"><h3>Statistics of run {this.state.statistics}</h3><br/></div>
								<div className="col">{ this.state.report.reportID }
									<table>
										<tr>
											<th>File 1</th><th>File 2</th><th>Similarity</th>
										</tr>
										<div id="tabledata"></div>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div id="myModal" className="modal">
				  <div className="modal-content">
						<div className="container git">
							<div className="row"><span className="close">&times;</span></div>
							<div className="row">
								<div className="col-md-5">Run description</div>
								<div className="col-md-5"><input type="text" id="run_description"/></div>
							</div>
							<div id="github_links"></div>
							<div className="row">
								<div className="col-md-5">Shared Users</div>
								<div className="col-md-5"><input type="text" id="shared_users"/></div>
							</div>
							<div className="row">
								<div className="col-md-5"><button id="run" onClick={ this.runCheck.bind(this) }>Run</button></div>
								<div className="col-md-5"><button id="run" onClick={ this.addStudent.bind(this) }>Add Student</button></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		);
	}
}

ReactDOM.render(
	<Check />,
	document.getElementById('root')
);