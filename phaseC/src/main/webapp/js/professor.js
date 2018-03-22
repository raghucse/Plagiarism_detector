class Check extends React.Component {

	/** 
	 * Constructor
	 * 
	 * The state contains:
	 *   runs: list of all runs' ID
	 *   statistics: ID of the selected run to check its data 
	*/
	constructor() {
		/**
		 * TODO: get a run list object/json from the end point and initialize the following state
		 */
		super();
		var loadRuns = [0, 1, 2];
		this.state = {
			runs: loadRuns,
			statistics: 0
		}
	}

	/**
	 * Show all runs
	 */
	showRuns() {
		const runElements = [];
		for (let r of this.state.runs) {
			runElements.push(
				<div className="row">
					<div className="col">
						<button className={ this.state.statistics == r ? "currentRun" : "futureRun" }
						  onClick={ () => this.setState({ statistics: r }) }>Run { r }</button>
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
	 * Add runs
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
	 * Render the main UI
	 */
	render() {
		return(
			<div className="container">
				<div className="row">
					<div className="col-1">{ this.showRuns() }</div>
					<div className="col-5" id="statistics">
					statistics of { this.state.statistics }
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
							<div className="row">
								<input type="submit" value="Run" id="run"/>
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