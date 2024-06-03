package com.labbi.taskTracker.controller;


import com.labbi.taskTracker.Repository.RoleRepository;
import com.labbi.taskTracker.model.Role;
import com.labbi.taskTracker.model.User;
import com.labbi.taskTracker.model.UserCridential;
import com.labbi.taskTracker.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
@Slf4j
@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String getRegisterPage(Model model){

        User user = new User();

        model.addAttribute("user",user);
        return "signUp";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") User user,BindingResult bindingResult, Model model){


        if (userService.userExistByEmail(user.getEmail()) != null) {
            log.error("Email address not available : " + user.getEmail());

            bindingResult.rejectValue("email", "email.required", "Email address not available.");
        }
        if(user.getFirstName() == null)
            bindingResult.rejectValue("firstName","first name must enter");
        else if(user.getLastName() == null)
            bindingResult.rejectValue("lastName", "last name must enter");
        else if(user.getEmail().isEmpty() || user.getEmail().isBlank())
            bindingResult.rejectValue("email", "email address must enter");
        else if (user.getPassword().isBlank() || user.getPassword().isEmpty() || user.getPassword().length() < 8)
            bindingResult.rejectValue("password", "password must enter with valid format");


        if(bindingResult.hasErrors())
            return "signUp";;


        userService.createNewuUser(user);


        return "redirect:/login";
    }

    @GetMapping("login")
    public String getLoginPage(Model model){

        UserCridential user = new UserCridential();
        model.addAttribute("user",user);
        return "login";
    }

    @PostMapping("login")
    public String loginUser(@ModelAttribute("user") UserCridential user, BindingResult bindingResult, Model model){

        if( user.getUsername() == null || user.getPassword() == null){
            model.addAttribute("message","Username Or password is empty");
            //model.addAttribute("user", new UserCridential());
            return "login";
        }


        User haveUser = userService.getUserByUserNameAndPassword(user.getUsername(), user.getPassword());
        System.out.println(haveUser);
        if(haveUser != null){
            return "redirect:index";
        }


        model.addAttribute("message", "User not registered!");
        return "login";
    }


}
