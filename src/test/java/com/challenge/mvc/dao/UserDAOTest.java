package com.challenge.mvc.dao;

import util.AbstractTest;
import com.challenge.mvc.dao.impl.mongo.UserDAO;
import com.challenge.mvc.entities.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserDAOTest extends AbstractTest {

    private UserDAO userDAO;
    private MongoTemplate mockMongoTemplate;
    private User userWithId;
    private User userWithoutId;

    @Before
    public void setup() {
        userDAO = spy(new UserDAO());
        mockMongoTemplate = mock(MongoTemplate.class);
        userWithId = new User("1", "name", "lastname", "05555555555");
        userWithoutId = new User("name", "lastname", "05555555555");

        userDAO.setMongoTemplate(mockMongoTemplate);
    }

    @Test
    public void saveTest() throws Exception {
        doNothing().when(mockMongoTemplate).save(any(User.class));

        userDAO.save(userWithoutId);

        verify(userDAO, times(1)).save(eq(userWithoutId));
    }

    @Test
    public void deleteTest() throws Exception {
        String id = "1";
        doNothing().when(mockMongoTemplate).remove(any(Query.class), any(Class.class));

        userDAO.delete(id);

        verify(mockMongoTemplate, times(1)).remove(any(Query.class), eq(User.class));
    }

    @Test
    public void findByIdTest() throws Exception {
        String id = "1";
        doReturn(userWithId).when(mockMongoTemplate).findById(any(String.class), any(Class.class));

        User userbyId = userDAO.findById(id);

        verify(mockMongoTemplate, times(1)).findById(eq(id), eq(User.class));
        assertEquals(userWithId, userbyId);
    }

    @Test
    public void findAllTest() throws Exception {
        List<User> userList = new LinkedList<User>();
        doReturn(userList).when(mockMongoTemplate).findAll(any(Class.class));

        List<User> all = userDAO.findAll();

        verify(mockMongoTemplate, times(1)).findAll(eq(User.class));
        assertEquals(userList, all);
    }
}
