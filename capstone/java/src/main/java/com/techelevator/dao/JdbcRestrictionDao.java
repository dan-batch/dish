package com.techelevator.dao;

import com.techelevator.model.Restriction;
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
        String userRestrictionSql = "SELECT restriction_id FROM user_restrictions\n" +
                "JOIN users ON users.user_id = user_restrictions.user_id\n" +
                "WHERE users.user_id = ?;";

        String allRestrictionsSql = "SELECT * FROM restrictions";

        SqlRowSet userRestrictionResults = jdbcTemplate.queryForRowSet(userRestrictionSql, userId);
        SqlRowSet allRestrictionsResults = jdbcTemplate.queryForRowSet(allRestrictionsSql);

        List<Integer> userRestrictionIds = new ArrayList<>();
        List<Restriction> allRestrictionIds = new ArrayList<>();

        while(userRestrictionResults.next()){
            userRestrictionIds.add(userRestrictionResults.getInt("restriction_id"));
        }

        while(allRestrictionsResults.next()){
            allRestrictionIds.add(mapRowToRestriction(allRestrictionsResults));
        }

        for(Restriction restriction : allRestrictionIds){
            restriction.setActive(userRestrictionIds.contains(restriction.getId()));
        }
        return allRestrictionIds;
    }

    private Restriction mapRowToRestriction(SqlRowSet sql){
        Restriction restriction = new Restriction();
        restriction.setId(sql.getInt("restriction_id"));
        restriction.setAbbreviation(sql.getString("restriction_abbrev"));
        restriction.setName(sql.getString("restriction_name"));
        return restriction;
    }
}
