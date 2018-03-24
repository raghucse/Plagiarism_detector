package edu.neu.reports;

import edu.neu.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class PlagiarismRunController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/plagiarism/run", method = RequestMethod.POST)
    public ResponseEntity<String> runPlagarism(@ModelAttribute PlagiarismRunRequest runReq) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        // runReq.setUserId(userService.findByUsername(userName).getId());
        return ResponseEntity.ok("Plagiarism run started");
    }
}
