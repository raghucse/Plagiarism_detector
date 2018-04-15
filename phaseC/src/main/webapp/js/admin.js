class Admin extends React.Component {
  constructor() {
    super();

    // Simulation data
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

    // Set state
    this.state = {
      usage: data,
      admin: 3
    }

    // Ping end point
    var datas = null;
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;
    var that = this;
		xhr.addEventListener("readystatechange", function () {
			if (this.readyState === 4) {
        var new_data = JSON.parse(this.responseText);
        that.setState({ usage: new_data });
        that.getMemoryChart(new_data);
        that.getReportChart(new_data);
			}
		});
    xhr.open("GET", "/stats");
    xhr.setRequestHeader("Authorization", readCookie('Authorization'));
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
        data:['Allocated', 'Free']
      },
      series: [
        {
          name: 'Memory Statistics',
          type: 'pie',
          radius: ['50%', '80%'],
          voidLabelOverlap: false,
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
              {value: data.serverDetails.allocatedMemory, name:'Allocated'},
              {value:data.serverDetails.freeMemory, name:'Free'}
            ]
          }
        ]
      };
      myChart.setOption(option);
  }

  /**
   * Show the report chart.
   */
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
          <div className="col">
            <div className="container">
              <div id="addadminuser">
                <div className="row justify-content-center form">
                  <h4>Add Admin User</h4>
                </div>
                <form onSubmit={ e => this.onAddAdminUserSubmit(e) }>
                  <div className="row justify-content-center form">
                    <div className="col-md-auto index_label"><span className="index_label_text">Email</span></div>
                    <div className="col-md-auto"><input type="email" id="admin_email" placeholder="user@example.com" /></div>
                    <div className="col-md-auto index_star"><span className="index_label_text">&nbsp;&nbsp;&nbsp;&nbsp;*</span></div>
                  </div>
                  <div className="row justify-content-center form">
                    <div className="col-md-auto index_label"><span className="index_label_text">Name</span></div>
                    <div className="col-md-auto"><input type="text" id="admin_name" placeholder="David Jackson" /></div>
                    <div className="col-md-auto index_star"><span className="index_label_text">&nbsp;&nbsp;&nbsp;&nbsp;*</span></div>
                  </div>
                  <div className="row justify-content-center form">
                    <div className="col-md-auto index_label"><span className="index_label_text">Password</span></div>
                    <div className="col-md-auto"><input type="password" id="admin_pwd" placeholder="6 to 15 characters" /></div>
                    <div className="col-md-auto index_star"><span className="index_label_text">&nbsp;&nbsp;&nbsp;&nbsp;*</span></div>
                  </div>
                  <div className="row justify-content-center form">
                    <div className="col-md-auto index_label"><span className="index_label_text">Confirm</span></div>
                    <div className="col-md-auto"><input type="password" id="admin_crfmpwd" placeholder="Confirm Password"/></div>
                    <div className="col-md-auto index_star"><span className="index_label_text">&nbsp;&nbsp;&nbsp;&nbsp;*</span></div>
                  </div>
                  <br />
                  <div className="row justify-content-center form">
                    <div className="col-md-auto">
                      <button type="submit" className="btn btn-primary subbtn" title="Warning!" data-container="body" 
                        data-toggle="popover" data-placement="right" data-content="Passwords don't match!">Add admin user</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
          <div className="col">
            <div className="container">
              <div id="removeuser">
                <div className="row justify-content-center form">
                  <h4>Remove user</h4>
                </div>
                <form>
                  <div className="row justify-content-center form">
                    <div className="col-md-auto index_label"><span className="index_label_text">Email</span></div>
                    <div className="col-md-auto"><input type="email" id="rm_email" placeholder="user@example.com" /></div>
                    <div className="col-md-auto index_star"><span className="index_label_text">&nbsp;&nbsp;&nbsp;&nbsp;*</span></div>
                  </div>
                  <br />
                  <div className="row justify-content-center form">
                    <div className="col-md-auto">
                      <button type="submit" className="btn btn-primary subbtn" title="Warning!" data-container="body" 
                        data-toggle="popover" data-placement="right" data-content="Passwords don't match!">Remove</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }

  /**
   * Add admin user.
   */
  onAddAdminUserSubmit(e) {
    e.preventDefault();
    console.log($('#admin_email').val());
    console.log($('#admin_name').val());
    console.log($('#admin_pwd').val());
    console.log($('#admin_crfmpwd').val());

    
  }

  /**
   * Usage information
   */
  usage() {
    this.setState({ admin: 3 });
    var datas = null;
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;
    var that = this;
		xhr.addEventListener("readystatechange", function () {
			if (this.readyState === 4) {
        var new_data = JSON.parse(this.responseText);
        that.setState({ usage: new_data });
        that.getMemoryChart(new_data);
        that.getReportChart(new_data);
			}
		});
    xhr.open("GET", "/stats");
    xhr.setRequestHeader("Authorization", readCookie('Authorization'));
		xhr.setRequestHeader("Cache-Control", "no-cache");
    xhr.send(datas);
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
              <button className={ this.state.admin == 3 ? "clickedButton" : "unclickedButton" } onClick={ () => this.usage() }>
                { this.state.admin == 3 ? "▶" : "" }Usage Info
              </button>
            </div>
            <div className="col-md-auto indexBanner">
              <button className={ this.state.admin == 4 ? "clickedButton" : "unclickedButton" } onClick={ () => this.setState({ admin: 4 }) }>
              { this.state.admin == 4 ? "▶" : "" }Account Manage
              </button>
            </div>
          </div>
          { this.state.admin == 3 ? this.renderUsage() : this.renderRequest() }
        </div>
      </div>
    )
  }
}

ReactDOM.render(
	<Admin />
);