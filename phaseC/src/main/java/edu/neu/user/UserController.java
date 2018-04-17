package edu.neu.user;


import edu.neu.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        if(userService.findByUsername(userForm.getUsername()) != null){
            return ResponseEntity.ok(new ServerResponse("user already registered"));
        }
        userForm.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
        userForm.setRole(Role.PROFESSOR);
        userService.save(userForm);
        Log.info("User registration successful");
        return ResponseEntity.ok(new ServerResponse("registration successful"));
    }

    /**
     * Exnd point for adding admin
     * @param userForm admin information
     * @return
     */
    @RequestMapping(value = "/add/admin", method = RequestMethod.POST)
    public ResponseEntity<ServerResponse> addAdmin(@ModelAttribute ApplicationUser userForm) {
        Log.info("Adding new admin:: ");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(userService.findByUsername(auth.getName()).getRole() != Role.ADMIN){
            return ResponseEntity.ok(new ServerResponse("Cannot create admin only admin create another admin"));
        }

        if(userService.findByUsername(userForm.getUsername()) != null){
            return ResponseEntity.ok(new ServerResponse("Admin already exists in the system"));
        }

        userForm.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
        userForm.setRole(Role.ADMIN);
        userService.save(userForm);
        Log.info("Admin registration successful");
        return ResponseEntity.ok(new ServerResponse("Admin registration successful"));
    }

    /**
     * end point for removing a admin
     * @param user username to be removed
     * @return Status of the removal
     */
    @RequestMapping(value = "/remove/user", method = RequestMethod.POST)
    public ResponseEntity<ServerResponse> removeAdmin(@ModelAttribute RemoveRequest user) {
        Log.info("Removing user:: "+user.getUserName());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(userService.findByUsername(auth.getName()).getRole() != Role.ADMIN){
            return ResponseEntity.ok(new ServerResponse("User can only be removed by admin"));
        }

        if(userService.removeUser(user.getUserName()) == 1 ){
            Log.info("user removed successfully");
            return ResponseEntity.ok(new ServerResponse("user removed successfully"));
        }
        Log.error("user removal failed");
        return ResponseEntity.ok(new ServerResponse("user removal failed"));
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

    /**
     * Fetch user information
     * @param userName username of the user
     * @return user information
     */
    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public ResponseEntity<UserResponse> userInfo(@RequestParam("userName") String userName) {
        ApplicationUser user = userService.findByUsername(userName);
        UserResponse userInfo = new UserResponse(user);
        return ResponseEntity.ok(userInfo);
    }

}