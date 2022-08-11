package com.techelevator.dao;

import com.techelevator.model.Restriction;

import java.security.Principal;
import java.util.List;

public interface RestrictionDao {

    boolean updateUserRestrictions(int userId, List<Integer> restrictionIdList);

    List<Restriction> getUserRestrictions(int userId);

}
