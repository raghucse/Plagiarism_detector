package edu.neu.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    /*@RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPassword());

        return "redirect:/welcome";
    }*/

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<ServerResponse> registration(@ModelAttribute User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            ResponseEntity.ok(new ServerResponse("registration failed"));
        }

        userService.save(userForm);

        return ResponseEntity.ok(new ServerResponse("registration successful"));
    }

    /*@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }*/

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<ServerResponse> login(@ModelAttribute User LoginRequest) {

        if(userService.findByUsername(LoginRequest.getUsername()) != null)
            return ResponseEntity.ok(new ServerResponse("login successful"));
        else
            return ResponseEntity.ok(new ServerResponse("login failed"));
    }


    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
}