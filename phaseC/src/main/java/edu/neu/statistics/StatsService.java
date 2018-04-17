package edu.neu.statistics;


import edu.neu.reports.ReportService;
import edu.neu.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * service for system statistics
 */
@Service
public class StatsService {
    @Autowired
    ReportService reportService;

    @Autowired
    UserService userService;

    /**
     * creates application information fetching total users and total report counts from the database.
     * @return Application information
     */
    public AppInfo getAppInfo(){
        AppInfo appInfo = new AppInfo();
        appInfo.setNumberOfreports(reportService.totalReportCount());
        appInfo.setNumberOfUsers(userService.totalUserCount());
        return appInfo;
    }

}
