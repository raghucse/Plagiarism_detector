package edu.neu.user;


import edu.neu.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Controller which implements end points for user operations
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * End point for registration
     * @param userForm user attributes in the post request
     * @return status of registration
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<ServerResponse> registration(@ModelAttribute ApplicationUser userForm) {
        Log.info("Starting user registration");
        Log.error("errooorrr");
        userForm.setUsername(userForm.getUsername());
        userForm.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
        userForm.setRole(userForm.getRole());
        userService.save(userForm);
        Log.info("User registration successful");
        return ResponseEntity.ok(new ServerResponse("registration successful"));
    }

    /**
     * Fetch user id
     * @param userName username of the user
     * @return user id and role for the user
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<UserInfoRes> userDetails(@RequestParam("userName") String userName) {
        ApplicationUser user = userService.findByUsername(userName);
        UserInfoRes userInfoRes = new UserInfoRes();
        userInfoRes.setUid(user.getId());
        userInfoRes.setRole(user.getRole());
        return ResponseEntity.ok(userInfoRes);
    }
}