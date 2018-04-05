class Admin extends React.Component {
  constructor() {
    super();

    var data = new Object();

    var _osDetails = new Object();
    _osDetails.osName = "Linux";
    _osDetails.osVersion = "4.13.0-38-generic";
    _osDetails.osArch = "amd64";
    data.osDetails = _osDetails;

    var _appInfo = new Object();
    _appInfo.numberOfUsers = 1;
    _appInfo.numberOfreports = 5;
    data.appInfo = _appInfo;

    var _serverDetails = new Object();
    _serverDetails.maxMemory = 3704094720;
    _serverDetails.allocatedMemory = 486539264;
    _serverDetails.freeMemory = 271936272;
    _serverDetails.availableProcessors = 4;
    data.serverDetails = _serverDetails;

    this.state = {
      usage: data,
      admin: 0
    }

    var datas = null;
    var xhr = new XMLHttpRequest();
    var that = this;
		xhr.addEventListener("readystatechange", function () {
			if (this.readyState === 4) {
        
        that.getMemoryChart(data)
        that.getReportChart(data)
			}
		});

		xhr.open("GET", "");
		xhr.setRequestHeader("Cache-Control", "no-cache");
		xhr.send(datas);

    
  }

  /**
   * Show the memory chart.
   */
  getMemoryChart(data) {
    var myChart = echarts.init(document.getElementById("usageChart"));
    var option = {
      tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
      },
      legend: {
          orient: 'vertical',
          x: 'left',
          data:['Allocated','Free']
      },
      series: [
          {
            name:'Memory',
            type:'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        fontSize: '30',
                        fontWeight: 'bold'
                    }
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data:[
              { value: data.serverDetails.allocatedMemory, name:'Allocated'},
              {value:data.serverDetails.freeMemory, name:'Free'}
            ]
        }
      ]
    };
    myChart.setOption(option);
  }

  getReportChart(data) {
    var myChart = echarts.init(document.getElementById("reportChart"));
    var option = {
      xAxis: {
          type: 'category',
          data: ['User', 'Report']
      },
      yAxis: {
          type: 'value'
      },
      series: [{
          data: [data.appInfo.numberOfUsers, data.appInfo.numberOfreports],
          type: 'bar'
      }]
  };
    myChart.setOption(option);
  }

  /**
   * Turn bytes into GB
   */
  calculateGB(data) {
    data /= (1024 * 1024 * 1024);
    return data.toFixed(3);
  }

  /**
   * Render the admin page UI.
   */
  renderUsage() {
    return(
      <div>
        <div className="row">
          <div className="col"><h3>Usage Statistics</h3></div>
        </div>
        <div className="row justify-content-md-center">
          <div className="col-md-auto admintable">
            <table className="table">
              <thead><tr><th>Data</th><th>Type</th><th>Value</th></tr></thead>
              <tbody>
                <tr><td rowSpan="3">System Details</td><td>OS Name</td><td>{ this.state.usage.osDetails.osName }</td></tr>
                <tr><td>OS Version</td><td>{ this.state.usage.osDetails.osVersion }</td></tr>
                <tr><td>OS Architecture</td><td>{ this.state.usage.osDetails.osArch }</td></tr>
                <tr><td rowSpan="2">Application Info</td><td>User Number</td><td>{ this.state.usage.appInfo.numberOfUsers }</td></tr>
                <tr><td>Report Number</td><td>{ this.state.usage.appInfo.numberOfreports }</td></tr>
                <tr><td rowSpan="4">Server Details</td><td>Max Memory</td><td>{ this.calculateGB(this.state.usage.serverDetails.maxMemory) } GB</td></tr>
                <tr><td>Allocated Memory</td><td>{ this.calculateGB(this.state.usage.serverDetails.allocatedMemory) } GB</td></tr>
                <tr><td>Free Memory</td><td>{ this.calculateGB(this.state.usage.serverDetails.freeMemory) } GB</td></tr>
                <tr><td>Available Processors</td><td>{ this.state.usage.serverDetails.availableProcessors }</td></tr>
              </tbody>
            </table>
          </div>
          <div className="col-md-auto" id="adminchart">
            <div className="row">
              <div className="col-md-auto" id="usageChart"></div>
            </div>
            <div className="row">
              <div className="col-md-auto" id="reportChart"></div>
            </div>
          </div>
        </div>
      </div>
    );
  }

  /**
   * Render the request approve UI.
   */
  renderRequest() {
    return(
      <div>
        <div className="row">
          <div className="col"><h3>TBD</h3></div>
        </div>
      </div>
    );
  }

  /**
   * Render the main Admin UI.
   */
  render() {
    return(
      <div>
        <div className="container adminWrapper">
          <div className="row justify-content-md-center">
            <div className="col-md-auto indexBanner">
              <button className={ this.state.admin == 0 ? "clickedButton" : "unclickedButton" } onClick={ () => this.setState({ admin: 0 }) }>
                { this.state.admin == 0 ? "▶" : "" }Usage Info
              </button>
            </div>
            <div className="col-md-auto indexBanner">
              <button className={ this.state.admin == 1 ? "clickedButton" : "unclickedButton" } onClick={ () => this.setState({ admin: 1 }) }>
              { this.state.admin == 1 ? "▶" : "" }Account Request
              </button>
            </div>
          </div>
          { this.state.admin == 0 ? this.renderUsage() : this.renderRequest() }
        </div>
      </div>
    )
  }
}

ReactDOM.render(
	<Admin />
);