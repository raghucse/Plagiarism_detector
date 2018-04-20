package edu.neu.reports;

import edu.neu.Log;
import edu.neu.astgeneration.ASTUtils;
import edu.neu.comparison.ComplexStrategy1;
import edu.neu.user.UserService;
import edu.neu.utils.Constants;
import factories.ComparisonStrategyFactory;
import plagiarismdetection.DetectionExecutor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller which provides endpoints to trigger plagiarism
 */
@Controller
public class PlagiarismRunController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReportService reportService;
    
    @Autowired
    private PlagiarismRunService plagiarismRunService;

    /**
     * End point to trigger plagiarism
     * @param runReq contains git links against which plagiarism should be run
     * @return
     */
    @RequestMapping(value = "/plagiarism/run", method = RequestMethod.POST)
    public ResponseEntity<String> runPlagarism(@ModelAttribute PlagiarismRunRequest runReq) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        
        PlagiarismRun plagiarismRun = mapRequestToBean(runReq);
        ComplexStrategy1 comparisonStrategy = (ComplexStrategy1) ComparisonStrategyFactory.getComparisonStrategy(Constants.DEFAULT_PLAGIARISM_STRATEGY, new ASTUtils());
        
        // set the incoming weights from the request to the strategy
        setWeights(comparisonStrategy, runReq.getStrategiesNames(), runReq.getStrategiesWeight());
        
        savePlagiarismRunToTable(plagiarismRun);
        
        plagiarismRun.setUserId(userService.findByUsername(userName).getId());
        
        // Submit the check to the executor
        boolean executionSubmitted = DetectionExecutor.getInstance(reportService).runPlagiarismCheck(plagiarismRun, comparisonStrategy);
        
        // return an error if check could not be submitted
        if(!executionSubmitted) {
        		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.ok("Plagiarism run started");
    }
    
    /**
     * Converts a plagiarism run request to a plagiarism run object
     * which would be later used by the engine
     * @param runReq : The PlagiarismRunRequest
     * @return : The constructed PlagiarismRun
     */
    private PlagiarismRun mapRequestToBean(PlagiarismRunRequest runReq) {
    		PlagiarismRun plagiarismRun = new PlagiarismRun();
    		plagiarismRun.setDescription(runReq.getDescription());
    		plagiarismRun.setGitUrls(runReq.getGitUrls());
    		plagiarismRun.setStudentNames(runReq.getStudentNames());
    		plagiarismRun.setRunName(runReq.getRunName());
    		return plagiarismRun;
    }
    
    /**
     * Save the PlagiarismRun to the database using the PlagiarismRunService
     * @param plagiarismRun : The PlagiarismRun to be saved
     */
    private void savePlagiarismRunToTable(PlagiarismRun plagiarismRun) {
    		plagiarismRunService.savePlagiarismRun(plagiarismRun);
    }
    
    private void setWeights(ComplexStrategy1 strategy, List<String> strategyNames, List<Double> strategyWeights) {
    		if(strategyNames.size() != strategyWeights.size()) {
    			return; // invalid weights and names received
    		}
    		for(int i=0; i<strategyNames.size(); i++) {
    			if(strategyNames.get(i).equals("LCS")) {
    				strategy.setLCSWeight(strategyWeights.get(i));
    			}
    			else if(strategyNames.get(i).equals("LEVENSHTEIN")) {
    				strategy.setLVDWeight(strategyWeights.get(i));
    			}
    			else if(strategyNames.get(i).equals("COSINE")) {
    				strategy.setCosineWeight(strategyWeights.get(i));
    			}
    		}
    }

}
