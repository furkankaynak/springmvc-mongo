package com.challenge.mvc.controller;

import com.challenge.mvc.dao.IUserDAO;
import com.challenge.mvc.util.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by furkan on 5/13/14.
 */

@Controller
@RequestMapping("/")
public class UserController implements AbstractController {

    private IUserDAO userDAO;

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model, HttpServletResponse response) {
        return "user";
    }

    public IUserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
