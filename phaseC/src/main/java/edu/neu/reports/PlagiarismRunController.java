package edu.neu.reports;

import edu.neu.astgeneration.ASTUtils;
import edu.neu.comparison.Strategy;
import edu.neu.user.UserService;
import edu.neu.utils.Constants;
import factories.ComparisonStrategyFactory;
import plagiarismdetection.DetectionExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PlagiarismRunController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private PlagiarismRunService plagiarismRunService;

    @RequestMapping(value = "/plagiarism/run", method = RequestMethod.POST)
    public ResponseEntity<String> runPlagarism(@ModelAttribute PlagiarismRunRequest runReq) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        
        PlagiarismRun plagiarismRun = mapRequestToBean(runReq);
        Strategy comparisonStrategy = ComparisonStrategyFactory.getComparisonStrategy(Constants.DEFAULT_PLAGIARISM_STRATEGY, new ASTUtils());
        
        savePlagiarismRunToTable(plagiarismRun);
        
        plagiarismRun.setUserId(userService.findByUsername(userName).getId());
        
        boolean executionSubmitted = DetectionExecutor.getInstance().runPlagiarismCheck(plagiarismRun, comparisonStrategy);
        
        if(!executionSubmitted) {
        		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.ok("Plagiarism run started");
    }
    
    private PlagiarismRun mapRequestToBean(PlagiarismRunRequest runReq) {
    		PlagiarismRun plagiarismRun = new PlagiarismRun();
    		plagiarismRun.setDescription(runReq.getDescription());
    		plagiarismRun.setGitUrls(runReq.getGitUrls());
    		return plagiarismRun;
    }
    
    private void savePlagiarismRunToTable(PlagiarismRun plagiarismRun) {
    		plagiarismRunService.savePlagiarismRun(plagiarismRun);
    }

}
