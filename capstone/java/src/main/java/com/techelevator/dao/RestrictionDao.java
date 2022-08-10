package com.techelevator.dao;

import java.security.Principal;

public interface RestrictionDao {

    boolean addRestrictionToUser(int restrictionId, Principal principal);

    boolean setRestrictionsActive(int userId);

}
