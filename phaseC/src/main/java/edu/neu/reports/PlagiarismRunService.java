package edu.neu.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class handles the Plagiarism Run that is performed
 */
@Service
public class PlagiarismRunService {
    @Autowired
    private PlagiarismRunRepository plagiarismRunRepository;

    /**
     * @param runId determines the value using which a
     *              run is to be searched
     * @return returns the plagiarism report that is found
     */
    public PlagiarismRun getPlagiarismRunByReportId(int runId){
        return plagiarismRunRepository.findByRunId(runId);
    }

    /**
     * @param plagiarismRun is the plagiarism run that is perfomed
     * @return the report that is saved to the repository
     */
    public PlagiarismRun savePlagiarismRun(PlagiarismRun plagiarismRun){
        return plagiarismRunRepository.save(plagiarismRun);
    }

}
