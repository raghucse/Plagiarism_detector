package edu.neu.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}