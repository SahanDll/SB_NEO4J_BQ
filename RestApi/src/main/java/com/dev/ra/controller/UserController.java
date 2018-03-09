package com.dev.ra.controller;

/**
 * Created by N5608296 on 03/01/2018 003.
 */

import com.dev.db.data.graph.service.inte.UserService;
import com.dev.db.data.sql.bo.service.inte.ClientMstService;
import com.dev.db.data.sql.fmt.service.inte.SettingMstService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;
    @Value("${const.bq.backDate}")
    private int backDate;

    @Autowired
    SettingMstService settingMstService;
    @Autowired
    ClientMstService clientMstService;


    /**
     * GET /read  --> Read all popularSearch from the database.
     */
    @RequestMapping(value = "/read-by-user", method = RequestMethod.GET)
    public JSONObject readByUser(@RequestParam("userId") String userId) {
        JSONObject response = null;
        try {
            response = userService.read(userId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * GET /read  --> Read all popularSearch from the database.
     */
    @RequestMapping(value = "/read-all", method = RequestMethod.GET)
    @Cacheable("user")
    public JSONObject readAll() {
        JSONObject response = null;
        try {
            //System.out.println(settingMstService.getAllClientDetails());
            //System.out.println(clientMstService.getAllClientDetails());
            response = userService.readAll();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}