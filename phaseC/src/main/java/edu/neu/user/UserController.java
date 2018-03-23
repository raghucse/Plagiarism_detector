package edu.neu.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<ServerResponse> registration(@ModelAttribute ApplicationUser userForm) {
        userForm.setUsername(userForm.getUsername());
        userForm.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
        userForm.setRole(userForm.getRole());
        userService.save(userForm);
        return ResponseEntity.ok(new ServerResponse("registration successful"));
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<Integer> userDetails(@RequestParam("userName") String userName) {
        ApplicationUser user = userService.findByUsername(userName);
        return ResponseEntity.ok(user.getId());
    }


}