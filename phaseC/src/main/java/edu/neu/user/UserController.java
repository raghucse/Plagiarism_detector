package edu.neu.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<ServerResponse> registration(@ModelAttribute User userForm) {
        userService.save(userForm);
        return ResponseEntity.ok(new ServerResponse("registration successful"));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<ServerResponse> login(@ModelAttribute LoginRequest loginRequest) {
        if(userService.findByUsername(loginRequest.getUsername()) != null)
            return ResponseEntity.ok(new ServerResponse("login successful"));
        else
            return ResponseEntity.ok(new ServerResponse("login failed"));
    }


    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
}