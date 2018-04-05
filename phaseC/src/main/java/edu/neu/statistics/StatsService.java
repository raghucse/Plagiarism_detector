package edu.neu.statistics;


import edu.neu.reports.ReportService;
import edu.neu.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {
    @Autowired
    ReportService reportService;

    @Autowired
    UserService userService;

    public AppInfo getAppInfo(){
        AppInfo appInfo = new AppInfo();
        appInfo.setNumberOfreports(reportService.totalReportCount());
        appInfo.setNumberOfUsers(userService.totalUserCount());
        return appInfo;
    }

}
