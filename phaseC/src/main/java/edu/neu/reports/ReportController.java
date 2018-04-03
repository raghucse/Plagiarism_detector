package edu.neu.reports;

import edu.neu.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "/report/user/reportIds/{userId}", method = RequestMethod.GET)
    public ResponseEntity<List<Report>> getReportIdsByOwner(@PathVariable("userId") int userId) {
        Log.info("Request log for user with id:: "+userId);
        List<Report> result = reportService.getReportIdsByOwner(userId);
        Log.info("Reports for the user:: "+result);
        return ResponseEntity.ok(result);
    }


    @RequestMapping(value = "/report/userId/{userId}", method = RequestMethod.GET)
    public ResponseEntity<List<Integer>> getReportsByOwner(@PathVariable("userId") int userId) {

        List<Integer> result = reportService.getReportByOwnerId(userId);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/report/reportId/{reportId}", method = RequestMethod.GET)
    public ResponseEntity<Report> getReportById(@PathVariable("reportId") int reportId) {
        Report result = reportService.getReportByReportId(reportId);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/report/shared/{suserId}", method = RequestMethod.GET)
    public ResponseEntity<List<Report>> getSharedReportsforId(@PathVariable("suserId") int reportId) {
        List<Report> result = reportService.findAllReportsForSharedUser(reportId);
        return ResponseEntity.ok(result);
    }

}