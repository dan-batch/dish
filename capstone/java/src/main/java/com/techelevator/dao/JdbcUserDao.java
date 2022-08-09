package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.techelevator.model.UserNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
//        if (email == null) throw new IllegalArgumentException("Email cannot be null");
//
//        int userId;
//        try {
//            userId = jdbcTemplate.queryForObject("select user_id from users where user_email = ?", int.class, email);
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
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "select * from users";

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

        for (User user : this.findAll()) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User " + email + " was not found.");
    }

    @Override
    public boolean create(String email, String password) {
        String insertUserSql = "insert into users (email,password_hash) values (?,?)";
        String password_hash = new BCryptPasswordEncoder().encode(password);

        return jdbcTemplate.update(insertUserSql, email, password_hash) == 1;
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setEmail(rs.getString("user_email"));
        user.setPassword(rs.getString("password_hash"));
        user.setActivated(true);
        return user;
    }
}
