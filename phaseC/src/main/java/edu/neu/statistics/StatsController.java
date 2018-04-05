package edu.neu.statistics;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StatsController {

    @Autowired
    StatsService statsService;

    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public ResponseEntity<StatsRes> registration() {
        StatsRes statsRes = new StatsRes();
        statsRes.setAppInfo(statsService.getAppInfo());
        return ResponseEntity.ok(statsRes);
    }
}
