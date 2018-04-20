package edu.neu.reports;

import edu.neu.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller to provide ennpoints for report related operations
 */
@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;


    /**
     * get reports associated with a user
     * @param userId User id
     * @return reports associated with the user
     */
    @RequestMapping(value = "/report/user/reportIds/{userId}", method = RequestMethod.GET)
    public ResponseEntity<List<Report>> getReportIdsByOwner(@PathVariable("userId") int userId) {
        Log.info("Request log for user with id:: "+userId);
        List<Report> result = reportService.getReportIdsByOwner(userId);
        Log.info("Reports for the user:: "+result);
        return ResponseEntity.ok(result);
    }


    /**
     *
     * @param userId user id
     * @return reports associated with the given user
     */
    @RequestMapping(value = "/report/userId/{userId}", method = RequestMethod.GET)
    public ResponseEntity<List<ReportIdsRes>> getReportsByOwner(@PathVariable("userId") int userId) {

        List<ReportIdsRes> result = reportService.getReportByOwnerId(userId);
        System.out.println(result);
        return ResponseEntity.ok(result);
    }

    /**
     * Endpoint to fetch report by report Id's
     * @param reportId report id of the report to be fetched
     * @return report associated with given id
     */
    @RequestMapping(value = "/report/reportId/{reportId}", method = RequestMethod.GET)
    public ResponseEntity<Report> getReportById(@PathVariable("reportId") int reportId) {
        Report result = reportService.getReportByReportId(reportId);
        return ResponseEntity.ok(result);
    }

    /**
     * Reports shared with other users
     * @param suserId shared user Id
     * @return reports shared with particular
     */
    @RequestMapping(value = "/report/shared/{suserId}", method = RequestMethod.GET)
    public ResponseEntity<List<Report>> getSharedReportsforId(@PathVariable("suserId") int suserId) {
        List<Report> result = reportService.findAllReportsForSharedUser(suserId);
        return ResponseEntity.ok(result);
    }

}