package com.challenge.mvc.controller;

import com.challenge.mvc.entities.User;
import com.challenge.mvc.services.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by furkan on 5/13/14.
 */

@Controller
@RequestMapping("/")
public class UserController {

    private IUserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model, HttpServletResponse response) {
        List<User> userList = userService.findAll();
        model.addAttribute("users", userList);
        return "user";
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}
