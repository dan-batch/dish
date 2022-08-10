package com.techelevator.dao;

import com.techelevator.model.Restriction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

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
    public boolean updateUserRestrictions(int userId, List<Integer> restrictionIdList) {
        List<Restriction> userRestrictions = getUserRestrictions(userId);
        for (Restriction restriction : userRestrictions) {
            if (!restriction.isActive() && restrictionIdList.contains(restriction.getId())) {
                String userRestrictionSql = "INSERT INTO user_restrictions(user_id, restriction_id) " +
                        "VALUES (?, ?);";
                jdbcTemplate.update(userRestrictionSql, userId, restriction.getId());
            } else if (restriction.isActive() && !restrictionIdList.contains((restriction.getId()))) {
                String sql = "DELETE FROM user_restrictions\n" +
                        "WHERE user_id = ? AND restriction_id = ?;";
                jdbcTemplate.update(sql,userId,restriction.getId());
            }
        }


        return true;
    }

    @Override
    public List<Restriction> getUserRestrictions(int userId){
        String userRestrictionSql = "SELECT restriction_id FROM user_restrictions\n" +
                "JOIN users ON users.user_id = user_restrictions.user_id\n" +
                "WHERE users.user_id = ?;";

        String allRestrictionsSql = "SELECT * FROM restrictions";

        SqlRowSet userRestrictionResults = jdbcTemplate.queryForRowSet(userRestrictionSql, userId);
        SqlRowSet allRestrictionsResults = jdbcTemplate.queryForRowSet(allRestrictionsSql);

        List<Integer> userRestrictionIds = new ArrayList<>();
        List<Restriction> userRestrictionObjects = new ArrayList<>();

        while(userRestrictionResults.next()){
            userRestrictionIds.add(userRestrictionResults.getInt("restriction_id"));
        }

        while(allRestrictionsResults.next()){
            userRestrictionObjects.add(mapRowToRestriction(allRestrictionsResults));
        }

        for(Restriction restriction : userRestrictionObjects){
            restriction.setActive(userRestrictionIds.contains(restriction.getId()));
        }
        return userRestrictionObjects;
    }

    private Restriction mapRowToRestriction(SqlRowSet sql){
        Restriction restriction = new Restriction();
        restriction.setId(sql.getInt("restriction_id"));
        restriction.setAbbreviation(sql.getString("restriction_abbrev"));
        restriction.setName(sql.getString("restriction_name"));
        return restriction;
    }
}
