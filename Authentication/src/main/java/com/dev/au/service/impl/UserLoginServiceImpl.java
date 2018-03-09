package com.dev.au.service.impl;

import com.dev.au.util.KeyGenerator;
import com.dev.db.data.h2.bean.UserLogin;
import com.dev.db.data.h2.repository.UserLoginRepository;
import com.dev.db.model.AuthUser;
import com.dev.au.util.Common;
import com.dev.au.service.UserLoginService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


/**
 * Created by N5608296 on 14/09/17.
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {
    private static final Logger logger = LoggerFactory.getLogger(UserLoginServiceImpl.class);
    @Autowired
    UserLoginRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public JSONObject authenticateUser(AuthUser user) {
        JSONObject jsonOut = new JSONObject();
        try {
            UserLogin ul = repository.findByUserName(user.getUserName());
            if (ul == null) {
                jsonOut.put("auth", false);
                jsonOut.put("role", 0);
            } else {
                if (ul.getPassword().equalsIgnoreCase(user.getPassword())) {
                    try {
                        UUID uuid = UUID.randomUUID();
                        String pass = uuid.toString();
                        jsonOut.put("auth", true);
                        jsonOut.put("role", ul.getUserRole());
                        jsonOut.put("pass", pass);
                        Common.getInstance().getUserADMap().put(user.getUserName().toUpperCase(), pass);
                    } catch (Exception e) {
                        logger.error(Common.getInstance().getError(), e);
                    }
                } else {
                    jsonOut.put("auth", false);
                    jsonOut.put("role", 0);
                }
            }

        } catch (Exception e) {
            logger.error(Common.getInstance().getError(), e);
        }
        return jsonOut;
    }

    @Override
    public JSONObject getUsers() {
        JSONArray result = new JSONArray();
        JSONObject main = new JSONObject();
        try {
            for (UserLogin ul : repository.findAll()) {
                JSONObject map = new JSONObject();
                map.put("userName", ul.getUserName());
                //map.put("password", ul.getPassword());
                map.put("userRole", ul.getUserRole());
                map.put("modified", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(ul.getUpdateTime()));
                map.put("inserted", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(ul.getInsertTime()));
                result.add(map);
            }
        } catch (Exception e) {
            logger.error(Common.getInstance().getError(), e);
        }

        main.put("result", result);
        return main;
    }

    @Override
    public JSONObject getUsersRoleBased(Integer userRole) {
        JSONArray result = new JSONArray();
        JSONObject main = new JSONObject();
        try {
            for (UserLogin ul : repository.findByUserRoleOrUserRoleGreaterThanOrderByUserRoleDesc(userRole, userRole)) {
                JSONObject map = new JSONObject();
                map.put("userName", ul.getUserName());
                //map.put("password", ul.getPassword());
                map.put("userRole", ul.getUserRole());
                map.put("modified", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(ul.getUpdateTime()));
                map.put("inserted", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(ul.getInsertTime()));
                result.add(map);
            }
        } catch (Exception e) {
            logger.error(Common.getInstance().getError(), e);
        }

        main.put("result", result);
        return main;
    }

    @Override
    public JSONObject addUser(AuthUser user) {
        JSONObject jsonOut = new JSONObject();
        UserLogin ul = getLoginUser(user);

        try {
            if (ul != null) {
                repository.save(ul);
                jsonOut.put("added", true);
            } else {
                jsonOut.put("added", false);
            }
        } catch (Exception e) {
            jsonOut.put("added", false);
            logger.error(Common.getInstance().getError(), e);
        }
        return jsonOut;
    }

    @Override
    public JSONObject updateUser(AuthUser user) {
        JSONObject jsonOut = new JSONObject();
        UserLogin userLogin = null;
        try {
            userLogin = repository.findByUserName(user.getUserName());
            if (userLogin != null) {
                try {
                    userLogin.setPassword(user.getPassword());
                    userLogin.setUserRole(user.getUserRole());
                    userLogin.setUpdateTime(new Date());
                    repository.save(userLogin);

                    jsonOut.put("updated", true);
                } catch (Exception e) {
                    jsonOut.put("updated", false);
                    logger.error(Common.getInstance().getError(), e);
                }
            } else {
                jsonOut.put("updated", false);
            }
        } catch (Exception e) {
            logger.error(Common.getInstance().getError(), e);
        }
        return jsonOut;
    }

    @Override
    public JSONObject deleteUser(String userName) {
        JSONObject jsonOut = new JSONObject();
        try {
            UserLogin ul = repository.findByUserName(userName);
            if (ul != null) {
                try {
                    repository.delete(ul);
                    jsonOut.put("delete", true);
                } catch (Exception e) {
                    jsonOut.put("delete", false);
                    logger.error(Common.getInstance().getError(), e);
                }
            } else {
                jsonOut.put("delete", false);
            }
        } catch (Exception e) {
            jsonOut.put("delete", false);
            logger.error(Common.getInstance().getError(), e);
        }
        return jsonOut;
    }

    public UserLogin getLoginUser(AuthUser user) {
        UserLogin userLogin = new UserLogin();
        userLogin.setUserName(user.getUserName());
        userLogin.setPassword(user.getPassword());
        userLogin.setUserRole(user.getUserRole());
        userLogin.setInsertTime(new Date());
        userLogin.setUpdateTime(new Date());
        return userLogin;
    }

}
