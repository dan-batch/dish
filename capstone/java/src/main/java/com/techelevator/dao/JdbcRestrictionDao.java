package com.techelevator.dao;

import com.techelevator.model.Restriction;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcRestrictionDao implements RestrictionDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcRestrictionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean addRestrictionToUser(int userId, int restrictionId) {
        String userRestrictionSql = "INSERT INTO user_restrictions(user_id, restriction_id) VALUES restrictions " +
                "VALUES ((SELECT user_id FROM users WHERE user_id = ?), (SELECT restriction_id FROM restrictions WHERE restriction_id = ?));";

        String allRestrictionsSql = "SELECT restriction_id FROM restrictions";

        SqlRowSet userRestrictionResults = jdbcTemplate.queryForRowSet(userRestrictionSql, userId, restrictionId);

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
        return true;
    }

    private Restriction mapRowToRestriction(SqlRowSet sql){
        Restriction restriction = new Restriction();
        restriction.setId(sql.getInt("restriction_id"));
        restriction.setInitials(sql.getString("restriction_initials"));
        restriction.setName(sql.getString("restriction_name"));
        return restriction;
    }
}
