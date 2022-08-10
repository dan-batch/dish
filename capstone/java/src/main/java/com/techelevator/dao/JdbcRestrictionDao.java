package com.techelevator.dao;

import com.techelevator.model.Restriction;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcRestrictionDao implements RestrictionDao {
    private final JdbcTemplate jdbcTemplate;
    private UserDao userDao;

    public JdbcRestrictionDao(JdbcTemplate jdbcTemplate, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
    }


    @Override
    public boolean addRestrictionToUser(int restrictionId, Principal principal) {
        String userRestrictionSql = "INSERT INTO user_restrictions(user_id, restriction_id) " +
                "VALUES (?, ?);";

        jdbcTemplate.update(userRestrictionSql, userDao.getIdByEmail(principal.getName()), restrictionId);

        return true;
    }

    @Override
    public List<Restriction> setRestrictionActive(int userId){
        String userRestrictionSql = "SELECT restriction_id FROM user_restrictions " +
                "JOIN users ON users.user_id = user_restrictions.user_id WHERE users.user_id = ?;";

        String allRestrictionsSql = "SELECT restriction_id FROM restrictions";

        SqlRowSet userRestrictionResults = jdbcTemplate.queryForRowSet(userRestrictionSql, userId);
        SqlRowSet allRestrictionsResults = jdbcTemplate.queryForRowSet(allRestrictionsSql);

        List<Restriction> userRestrictionIds = new ArrayList<>();
        List<Restriction> allRestrictionIds = new ArrayList<>();

        while(userRestrictionResults.next()){
            userRestrictionIds.add(mapRowToRestriction(userRestrictionResults));
        }

        while(allRestrictionsResults.next()){
            allRestrictionIds.add(mapRowToRestriction(allRestrictionsResults));
        }

        for(Restriction restriction : userRestrictionIds){
            restriction.setActive(allRestrictionIds.contains(restriction));
        }
        return userRestrictionIds;
    }

    private Restriction mapRowToRestriction(SqlRowSet sql){
        Restriction restriction = new Restriction();
        restriction.setId(sql.getInt("restriction_id"));
        restriction.setInitials(sql.getString("restriction_abbrev"));
        restriction.setName(sql.getString("restriction_name"));
        return restriction;
    }
}
