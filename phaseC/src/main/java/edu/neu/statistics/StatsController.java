package edu.neu.statistics;


import edu.neu.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Api end points for system statistics
 */
@Controller
public class StatsController {

    @Autowired
    StatsService statsService;

    /**
     *
     * @return Api end point to get system statistics
     */
    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public ResponseEntity<StatsRes> registration() {
        Log.info("Getting system statistics");
        StatsRes statsRes = new StatsRes();
        statsRes.setAppInfo(statsService.getAppInfo());
        Log.info("Returning system statistics");
        return ResponseEntity.ok(statsRes);
    }
}
