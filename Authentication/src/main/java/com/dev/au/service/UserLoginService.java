package com.dev.au.service;

import com.dev.db.model.AuthUser;
import org.json.simple.JSONObject;

/**
 * Created by N5608296 on 14/09/17.
 */
public interface UserLoginService {
    public JSONObject authenticateUser(AuthUser user);

    public JSONObject getUsers();

    public JSONObject getUsersRoleBased(Integer userRole);

    public JSONObject addUser(AuthUser user);

    public JSONObject updateUser(AuthUser user);

    public JSONObject deleteUser(String userName);
}
