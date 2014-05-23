package com.challenge.mvc.controller;

import com.challenge.mvc.entities.User;
import com.challenge.mvc.services.IReCaptchaService;
import com.challenge.mvc.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by furkan on 5/13/14.
 */

@Controller
@RequestMapping("/")
public class UserController {

    private static final String WRONG_CAPTCHA = "Captcha is wrong!";
    private static final String USER_SAVED = "User saved";
    private static final String USER_DELETED = "User deleted";
    private static final String USER_UPDATED = "User updated";

    private IUserService userService;
    private IReCaptchaService reCaptchaService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model, HttpServletResponse response) {
        List<User> userList = userService.findAll();
        model.addAttribute("users", userList);
        return "user";
    }

    @RequestMapping(value="/user", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String save(@Valid @ModelAttribute("user") User user,
                       @RequestParam("recaptcha_challenge_field") String challenge,
                       @RequestParam("recaptcha_response_field") String capthcaResponse,
                       HttpServletResponse response,
                       HttpServletRequest request) {

        if (!reCaptchaService.controlCaptcha(request.getRemoteAddr(), challenge, capthcaResponse)) {
            response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
            return WRONG_CAPTCHA;
        }

        userService.save(user);
        response.setStatus(HttpStatus.CREATED.value());

        return USER_SAVED;
    }

    @RequestMapping(value="/user/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable("id") String id,
                                     HttpServletResponse response,
                                     HttpServletRequest request) {

        userService.delete(id);
        response.setStatus(HttpStatus.OK.value());

        return USER_DELETED;
    }

    @RequestMapping(value="/user/update", method = RequestMethod.POST)
    public @ResponseBody String update(@Valid @ModelAttribute("user") User user,
                                     HttpServletResponse response,
                                     HttpServletRequest request) {

        userService.save(user);
        response.setStatus(HttpStatus.OK.value());

        return USER_UPDATED;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public void setReCaptchaService(IReCaptchaService reCaptchaService) {
        this.reCaptchaService = reCaptchaService;
    }
}
