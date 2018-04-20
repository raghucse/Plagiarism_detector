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
      admin: 3,

      admin_email: "",
      admin_password: "",
      admin_cfrm: "",

      rm_email: ""
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
      <div >
        <div className="row justify-content-md-center test">
          <div className="col">
            <div className="container">
              <div id="addadminuser">
                <div className="row justify-content-center form">
                  <h4>Add Admin User</h4>
                </div>
                <form onSubmit={ e => this.onAddAdminUserSubmit(e) }>
                  <div className="row justify-content-center form">
                    <div className="col-md-auto index_label"><span className="index_label_text">Email</span></div>
                    <div className="col-md-auto"><input type="email" id="admin_email" placeholder="user@example.com" onChange={ () => this.setState({ admin_email: $('#admin_email').val() }) }/></div>
                    <div className="col-md-auto index_star"><span className="index_label_text">&nbsp;&nbsp;&nbsp;&nbsp;*</span></div>
                  </div>
                  <div className="row justify-content-center form">
                    <div className="col-md-auto index_label"><span className="index_label_text">Password</span></div>
                    <div className="col-md-auto"><input type="password" id="admin_pwd" placeholder="6 to 15 characters" onChange={ () => this.setState({ admin_password: $('#admin_pwd').val() }) }/></div>
                    <div className="col-md-auto index_star"><span className="index_label_text">&nbsp;&nbsp;&nbsp;&nbsp;*</span></div>
                  </div>
                  <div className="row justify-content-center form">
                    <div className="col-md-auto index_label"><span className="index_label_text">Confirm</span></div>
                    <div className="col-md-auto"><input type="password" id="admin_crfmpwd" placeholder="Confirm Password" onChange={ () => this.setState({ admin_cfrm: $('#admin_crfmpwd').val() }) }/></div>
                    <div className="col-md-auto index_star"><span className="index_label_text">&nbsp;&nbsp;&nbsp;&nbsp;*</span></div>
                  </div>
                  <br />
                  <div className="row justify-content-center form">
                    <div className="col-md-auto">
                      <button type="submit" className="btn btn-primary subbtn" title="Warning!" data-container="body" 
                        data-toggle="popover" data-placement="right" data-content="Passwords don't match!" disabled={ this.enableAddAdmin() }>{ this.enableAddAdmin() ? "Fill in info" : "Add admin user" }</button>
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
                <form onSubmit={ e => this.onSearchUserSubmit(e) }>
                  <div className="row justify-content-center form">
                    <div className="col-md-auto index_label"><span className="index_label_text">Email</span></div>
                    <div className="col-md-auto"><input type="email" id="rm_email" placeholder="user@example.com" onChange={ () => this.setState({ rm_email: $('#rm_email').val() }) }/></div>
                    <div className="col-md-auto index_star"><span className="index_label_text">&nbsp;&nbsp;&nbsp;&nbsp;*</span></div>
                  </div>
                  <br />
                  <div className="row justify-content-center form">
                    <div className="col-md-auto">
                      <button type="submit" className="btn btn-primary subbtn" >Search</button>
                    </div>
                  </div>
                </form>
                <div className="row justify-content-center form">
                <div className="col-md-auto" id="searchresult"></div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }

  /**
   * Search if there is any user for removing.
   */
  onSearchUserSubmit(e) {
    e.preventDefault();
    var data = null;
    var url = "/user/info?userName=" + this.state.rm_email;
    var xhr = new XMLHttpRequest();
    var that = this;
    xhr.withCredentials = true;
    xhr.addEventListener("readystatechange", function () {
      if (this.readyState === 4 & this.status == 200) {
        var result = JSON.parse(this.responseText);
        var table = "<table class='table'><thead><tr><th>UID</th><th>Email</th><th>Manage</th></tr></thead><tbody>";
        table += "<tr><td>" + result.userId + "</td><td>" + result.userName + "</td><td>";
        table += "<button type='button' class='btn btn-primary' id='rmResult'>Remove</button>";
        table += "</td></tr></tbody></table>";
        $("#searchresult").html(table);
        $("#rmResult").on("click", function() {
          that.onRemoveUserSubmit(result.userName)
        })
      } else {
        console.log(this.status);
        $("#searchresult").html("No result found.");
      }
    });		
    xhr.open("GET", url);
    xhr.setRequestHeader("Cache-Control", "no-cache");
    xhr.setRequestHeader("Authorization", readCookie('Authorization'));
    xhr.send(data);

  }

  /**
   * Remove user.
   */
  onRemoveUserSubmit(user) {
    var data = new FormData();
    data.append('userName', user);

    var xhr = new XMLHttpRequest();
		xhr.withCredentials = true;
		xhr.addEventListener("readystatechange", function () {
			if (this.readyState === 4) {
        console.log(this.responseText);
        var msg = JSON.parse(this.responseText).msg;
        $("#searchresult").html(msg);
			}
		});
		xhr.open("POST", "/remove/user");
		xhr.setRequestHeader("Cache-Control", "no-cache");
		xhr.setRequestHeader("Authorization", document.cookie);
    xhr.send(data);
  }

  /**
   * Enable add admin button
   */
  enableAddAdmin() {
    var res1 = /^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z0-9]+$/.test(this.state.admin_email);
    var res2 = this.state.admin_password.length >= 6 && this.state.admin_password.length <= 15;
    return !(this.state.admin_email != "" && this.state.admin_password != "" && this.state.admin_password == this.state.admin_cfrm && res1 && res2);
  }

  /**
   * Add admin user.
   */
  onAddAdminUserSubmit(e) {
    e.preventDefault();
    
    // Send the data to the end point
    var data = new FormData();
    data.append('username', $('#admin_email').val());
    data.append('password', $('#admin_pwd').val());
    data.append('role', 'ADMIN');

    var xhr = new XMLHttpRequest();
    var that = this;
		xhr.withCredentials = true;
		xhr.addEventListener("readystatechange", function () {
			if (this.readyState === 4) {
        if (JSON.parse(this.responseText).msg != 'Admin already exists in the system'){
          $('[data-toggle="popover"]').attr('data-content', 'Add admin user succeed!')
          $('[data-toggle="popover"]').popover('show'); 
          setTimeout(function() {
            $('[data-toggle="popover"]').popover('hide');
            $('[data-toggle="popover"]').attr('data-content', 'Invalid password length!') 
          }, 5000);
          $('#admin_email').val('');
          $('#admin_pwd').val('');
          $('#admin_crfmpwd').val('');
          that.setState({
            admin_email: "",
            admin_password: "",
            admin_cfrm: ""
          });
        } else {
          $('[data-toggle="popover"]').attr('data-content', 'User existed, adding failed!')
          $('[data-toggle="popover"]').popover('show'); 
          setTimeout(function() {
            $('[data-toggle="popover"]').popover('hide');
            $('[data-toggle="popover"]').attr('data-content', 'Invalid password length!') 
          }, 5000);
        } 
			}
		});
		xhr.open("POST", "/add/admin");
		xhr.setRequestHeader("Cache-Control", "no-cache");
		xhr.setRequestHeader("Authorization", document.cookie);
    xhr.send(data);
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