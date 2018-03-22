class Check extends React.Component {

	/** 
	 * Constructor
	 * 
	 * The state contains:
	 *   runs: list of all runs' ID
	 *   statistics: ID of the selected run to check its data
	 *   stuNum: the number of students in the run, by default is 1 
	*/
	constructor() {
		super();

		// Get the report by user ID
		var data = null;
		var xhr = new XMLHttpRequest();
		xhr.withCredentials = true;
		xhr.addEventListener("readystatechange", function () {
			if (this.readyState === 4) {
				console.log(this.responseText);
			}
		});
		xhr.open("GET", "http://localhost:8080/report/userId/0");
		xhr.setRequestHeader("Cache-Control", "no-cache");		
		xhr.send(data);

		// TODO: get all run ID
		var loadRuns = [0, 1, 2];

		// TODO: get the first report

		// TODO: get the percentage value of the first 
		var loadPercentage = 0.8;

		// TODO: get the git diff

		this.state = {
			runs: loadRuns,
			statistics: loadRuns[0],
			stuNum: 1,
			percentage: loadPercentage,
			userID: 0,
			reports: []
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
	 * Show statistic of a certain run,
	 *   triggered by pressing the button of a historical run.
	 */
	showStatistics(r) {
		this.setState({ statistics: r });
	}

	/**
	 * Adjust the arrow,
	 *   by appending white characters before the arrow.
	 */
	adjustArrow() {
		var ADJUSTING = 75;
		var hashTags = this.state.percentage * ADJUSTING;
		var space = "";
		for (var i = 0; i < hashTags; i++) {
			space += "#";
		}
		return space;
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
		var links = [];
		for (var i = 1; i < this.state.stuNum; i++) {
			links.push(document.getElementById(i.toString()).value);
		}
		console.log(links);
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
								<div className="col">
									<h3>Statistics of run {this.state.statistics}</h3><br/>
								</div>
								<div className="col meter"></div>
								<div className="col meter2">
									<div id="zero_tag">0%</div>
									<div id="hundred_tag">100%</div>
								</div>
								<div className="col">
								  <span id="nbsp">{ this.adjustArrow() }</span>
									<span id="arrow">↑</span>
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