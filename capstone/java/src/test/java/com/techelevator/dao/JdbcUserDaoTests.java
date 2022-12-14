package com.techelevator.dao;

import com.techelevator.model.User;
import com.techelevator.model.exceptions.UserNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class JdbcUserDaoTests extends BaseDaoTests {
    protected static final User USER_1 = new User(1, "user1", "user1", "ROLE_USER");
    protected static final User USER_2 = new User(2, "user2", "user2", "ROLE_USER");
    private static final User USER_3 = new User(3, "user3", "user3", "ROLE_USER");

    private JdbcUserDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcUserDao(jdbcTemplate);
    }

//    @Test(expected = IllegalArgumentException.class)
//    public void findIdByUsername_given_null_throws_exception() {
//        sut.findIdByEmail(null);
//    }
//
    @Test(expected = UserNotFoundException.class)
    public void findIdByUsername_given_invalid_username_throws_exception() {
        sut.getIdByEmail("invalid");
    }
//
//    @Test
//    public void findIdByEmail_given_valid_user_returns_user_id() {
//        int actualUserId = sut.findIdByEmail(USER_1.getEmail());
//
//        Assert.assertEquals(USER_1.getId(), actualUserId);
//    }

    @Test(expected = IllegalArgumentException.class)
    public void findByEmail_given_null_throws_exception() {
        sut.findByEmail(null);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void findByEmail_given_invalid_username_throws_exception() {
        sut.findByEmail("invalid");
    }

    @Test
    public void findByUsername_given_valid_user_returns_user() {
        User actualUser = sut.findByEmail(USER_1.getEmail());

        Assert.assertEquals(USER_1, actualUser);
    }

    @Test(expected = UserNotFoundException.class)
    public void getUserById_given_invalid_user_id_throws_exception() {
        sut.getUserById(-1);
    }

    @Test
    public void getUserById_given_valid_user_id_returns_user() {
        User actualUser = sut.getUserById(USER_1.getId());

        Assert.assertEquals(USER_1, actualUser);
    }

    @Test
    public void findAll_returns_all_users() {
        List<User> users = sut.findAll();

        Assert.assertNotNull(users);
        Assert.assertEquals(3, users.size());
        Assert.assertEquals(USER_1, users.get(0));
        Assert.assertEquals(USER_2, users.get(1));
        Assert.assertEquals(USER_3, users.get(2));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void create_user_with_null_username() {
        sut.create(null, USER_3.getPassword(), "ROLE_USER" );
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void create_user_with_existing_email() {
        sut.create(USER_1.getEmail(), USER_3.getPassword(), "ROLE_USER");
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_user_with_null_password() {
        sut.create(USER_3.getEmail(), null, "ROLE_USER");
    }

    @Test
    public void create_user_creates_a_user() {
        User newUser = new User(-1, "new", "user", "ROLE_USER");

        boolean userWasCreated = sut.create(newUser.getEmail(), newUser.getPassword(), "ROLE_USER");

        Assert.assertTrue(userWasCreated);

        User actualUser = sut.findByEmail(newUser.getEmail());
        newUser.setId(actualUser.getId());

        actualUser.setPassword(newUser.getPassword()); // reset password back to unhashed password for testing
        Assert.assertEquals(newUser, actualUser);
    }
}
