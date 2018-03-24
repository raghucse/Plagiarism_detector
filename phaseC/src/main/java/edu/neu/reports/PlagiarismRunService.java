package edu.neu.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PlagiarismRunService {
    @Autowired
    private PlagiarismRunRepository plagiarismRunRepository;

    public PlagiarismRun getPlagiarismRunByReportId(int plagiarismRunId){
        return plagiarismRunRepository.findById(plagiarismRunId);
    }
    
    public PlagiarismRun savePlagiarismRun(PlagiarismRun plagiarismRun){
        return plagiarismRunRepository.save(plagiarismRun);
    }

}
