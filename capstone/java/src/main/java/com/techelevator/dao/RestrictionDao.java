package com.techelevator.dao;

import com.techelevator.model.Restriction;

import java.security.Principal;
import java.util.List;

public interface RestrictionDao {

    boolean addRestrictionToUser(int restrictionId, Principal principal);

    List<Restriction> setRestrictionActive(int userId);

}
