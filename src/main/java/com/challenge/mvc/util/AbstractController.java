package com.challenge.mvc.util;

import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by furkan on 5/13/14.
 */
public interface AbstractController {

    public String index(ModelMap model, HttpServletResponse response);
}
