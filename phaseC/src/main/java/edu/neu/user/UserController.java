package edu.neu.user;


import edu.neu.Log;
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
        Log.info("Hello Logging");
        User user = userService.findByUsername(loginRequest.getUsername());
        if( user != null && user.getUsername().equals(loginRequest.getUsername())
                && user.getPassword().equals(loginRequest.getPassword())){
            return ResponseEntity.ok(new ServerResponse("login successful"));
        }
        else {
            return ResponseEntity.ok(new ServerResponse("login failed"));
        }
    }


    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
}