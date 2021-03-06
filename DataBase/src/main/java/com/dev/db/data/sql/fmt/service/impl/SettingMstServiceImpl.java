package com.dev.db.data.sql.fmt.service.impl;


import com.dev.db.data.sql.fmt.bean.alert.SettingMst;
import com.dev.db.data.sql.fmt.repository.SettingMstRepository;
import com.dev.db.data.sql.fmt.service.inte.SettingMstService;

import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by N5608296 on 14/09/17.
 */
@Service
public class SettingMstServiceImpl implements SettingMstService {
    private static final Logger logger = LoggerFactory.getLogger(SettingMstServiceImpl.class);
    @Autowired
    SettingMstRepository repository;


    @Override
    public JSONObject getAllClientDetails() {
        JSONArray result = new JSONArray();
        JSONObject main = new JSONObject();
        Gson gson = new Gson();
        for(SettingMst sm : repository.findAll()){
            result.add(sm);
        }

        main.put("result", gson.toJson(result));
        return main;

    }

}
