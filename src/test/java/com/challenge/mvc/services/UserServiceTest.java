package com.challenge.mvc.services;

import util.AbstractTest;
import com.challenge.mvc.dao.impl.mongo.UserDAO;
import com.challenge.mvc.entities.User;
import com.challenge.mvc.services.impl.UserService;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class UserServiceTest extends AbstractTest {

    private UserService userService;
    private UserDAO userDAO;
    private User userWithId;
    private User userWithoutId;

    @Before
    public void setup() {
        userService = spy(new UserService());
        userDAO = mock(UserDAO.class);

        userWithId = new User("1", "name", "lastname", "05555555555");
        userWithoutId = new User("name", "lastname", "05555555555");

        userService.setUserDAO(userDAO);
    }

    @Test
    public void saveTest() throws Exception {
        doNothing().when(userDAO).save(any(User.class));

        userService.save(userWithoutId);

        verify(userDAO, times(1)).save(eq(userWithoutId));
    }

    @Test
    public void deleteTest() throws Exception {
        String id = "1";
        doNothing().when(userDAO).delete(anyString());

        userDAO.delete(id);

        verify(userDAO, times(1)).delete(id);
    }

    @Test
    public void findByIdTest() throws Exception {
        String id = "1";
        doReturn(userWithId).when(userDAO).findById(anyString());

        User userbyId = userDAO.findById(id);

        verify(userDAO, times(1)).findById(eq(id));
        assertEquals(userWithId, userbyId);
    }

    @Test
    public void findAllTest() throws Exception {
        List<User> userList = new LinkedList<User>();
        doReturn(userList).when(userDAO).findAll();

        List<User> all = userDAO.findAll();

        verify(userDAO, times(1)).findAll();
        assertEquals(userList, all);
    }

}
