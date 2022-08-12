package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.techelevator.model.UserDTO;
import com.techelevator.model.UserNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.techelevator.model.User;

@Component
public class JdbcUserDao implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    @Override
//    public int findIdByEmail(String email) {
//        if (email == null) throw new IllegalArgumentException("Username cannot be null");
//
//        int userId;
//        try {
//            userId = jdbcTemplate.queryForObject("select user_id from users where email = ?", int.class, email);
//        } catch (EmptyResultDataAccessException e) {
//            throw new UsernameNotFoundException("User " + email + " was not found.");
//        }
//
//        return userId;
//    }

    @Override
    public User getUserById(int userId) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()) {
            return mapRowToUser(results);
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public User updateUserProfile(int userId, UserDTO updatedUser) {
        String sql = "UPDATE users\n" +
                "SET user_email = ?,\n" +
                "picture_url = ?,\n" +
                "first_name = ?,\n" +
                "last_name = ?\n" +
                "WHERE user_id = ?;";
        jdbcTemplate.update(sql,updatedUser.getEmail(),updatedUser.getImageURL(),
                updatedUser.getFirstName(),updatedUser.getLastName(),userId);

        return getUserById(userId);
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }

        return users;
    }

    @Override
    public User findByEmail(String email) {
        if (email == null) throw new IllegalArgumentException("Email cannot be null");

        for (User user : findAll()) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
//            throw new UserNotFoundException();
        }
        return null;

    }

    @Override
    public boolean create(String email, String password, String role) {
        String insertUserSql = "insert into users (email,password_hash,role) values (?,?,?)";
        String password_hash = new BCryptPasswordEncoder().encode(password);
        String ssRole = role.toUpperCase().startsWith("ROLE_") ? role.toUpperCase() : "ROLE_" + role.toUpperCase();

        return jdbcTemplate.update(insertUserSql, email, password_hash, ssRole) == 1;
    }

    @Override
    public int getIdByEmail(String email) throws NullPointerException{
        String sql = "SELECT user_id FROM users WHERE email = ?;";
        int userId;
        try {
            userId = jdbcTemplate.queryForObject(sql, Integer.class, email);
            return userId;
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException();
        }
    }


    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password_hash"));
        user.setImageURL(rs.getString("picture_url"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setAuthorities(Objects.requireNonNull(rs.getString("role")));
        user.setActivated(true);
        return user;
    }
}