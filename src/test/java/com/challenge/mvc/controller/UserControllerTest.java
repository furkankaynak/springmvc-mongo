package com.challenge.mvc.controller;

import com.challenge.mvc.entities.User;
import com.challenge.mvc.services.impl.ReCaptchaService;
import com.challenge.mvc.services.impl.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import util.AbstractTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class UserControllerTest extends AbstractTest {

    private static final String WRONG_CAPTCHA = "Captcha is wrong!";
    private static final String USER_SAVED = "User saved";
    private static final String USER_DELETED = "User deleted";
    private static final String USER_UPDATED = "User updated";

    private MockMvc mockMvc;
    private UserController userController;
    private ReCaptchaService reCaptchaService;
    private UserService userService;
    private static final ModelMap modelMap = new ModelMap();
    private HttpServletRequest request;
    private HttpServletResponse response;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Before
    @Override
    public void setup() {
        mockMvc = webAppContextSetup(wac).build();

        userController = spy(new UserController());
        reCaptchaService = mock(ReCaptchaService.class);
        userService = mock(UserService.class);

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        userController.setReCaptchaService(reCaptchaService);
        userController.setUserService(userService);
    }

    @Test
    public void indexTest() throws Exception {
        List<User> userList = new LinkedList<User>();
        userList.add(new User());

        doReturn(userList).when(userService).findAll();

        userController.index(modelMap, response);

        verify(userService).findAll();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("user"));

    }

    @Test
    public void saveTestValidUser() throws Exception {
        User u = new User("furkan", "kayna", "05001112233");
        String ip = "1.1.1.2";
        String challenge = "chaptcha";
        String responseCaptcha = "response";

        doReturn(true).when(reCaptchaService).controlCaptcha(anyString(),anyString(), anyString());
        doNothing().when(userService).save(any(User.class));
        doReturn(ip).when(request).getRemoteAddr();

        String saveResponse = userController.save(u, challenge, responseCaptcha, response, request);

        verify(reCaptchaService).controlCaptcha(eq(ip), eq(challenge), eq(responseCaptcha));
        verify(response, never()).setStatus(eq(HttpStatus.NOT_ACCEPTABLE.value()));
        verify(userService).save(eq(u));
        verify(response).setStatus(eq(HttpStatus.CREATED.value()));

        assertEquals(USER_SAVED, saveResponse);
    }

    @Test
    public void saveTestValidUserWrongCaptcha() throws Exception {
        User u = new User("furkan", "kayna", "05001112233");
        String ip = "1.1.1.2";
        String challenge = "chaptcha";
        String responseCaptcha = "chaptcha";

        doReturn(false).when(reCaptchaService).controlCaptcha(anyString(),anyString(), anyString());
        doNothing().when(userService).save(any(User.class));
        doReturn(ip).when(request).getRemoteAddr();

        String saveResponse = userController.save(u, challenge, responseCaptcha, response, request);

        verify(reCaptchaService).controlCaptcha(eq(ip), eq(challenge), eq(responseCaptcha));
        verify(response).setStatus(eq(HttpStatus.NOT_ACCEPTABLE.value()));
        verify(userService, never()).save(eq(u));
        verify(response, never()).setStatus(eq(HttpStatus.CREATED.value()));

        assertEquals(WRONG_CAPTCHA, saveResponse);
    }

    @Test
    public void deleteTest() throws Exception {
        String id = "user_id";

        doNothing().when(userService).delete(anyString());

        userController.delete(id, response, request);

        verify(userService).delete(id);
        verify(response).setStatus(eq(HttpStatus.OK.value()));

        mockMvc.perform(delete("/user/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string(USER_DELETED));
    }

    @Test
    public void updateTest() {
        User u = new User("furkan", "kaynak", "05001112233");

        doNothing().when(userService).save(any(User.class));

        String updateResponse = userController.update(u, response, request);

        verify(userService).save(eq(u));
        verify(response).setStatus(eq(HttpStatus.OK.value()));

        assertEquals(USER_UPDATED, updateResponse);
    }

}
