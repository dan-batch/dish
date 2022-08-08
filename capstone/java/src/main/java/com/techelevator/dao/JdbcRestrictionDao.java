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
    public boolean addRestrictionToUser(int id) {
        String userRestrictionSql = "SELECT restriction_id FROM restriction_user " +
                "WHERE user_id = ?";

        String allRestrictionsSql = "SELECT restriction_name, restriction_initials " +
                "FROM restrictions";

        SqlRowSet userRestrictionResults = jdbcTemplate.queryForRowSet(userRestrictionSql, id);

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
            if (allRestrictionIds.contains(restriction)){
                restriction.setActive(true);
            }
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
