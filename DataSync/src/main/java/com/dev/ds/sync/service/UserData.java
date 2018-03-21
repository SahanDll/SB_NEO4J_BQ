package com.dev.ds.sync.service;

import com.dev.db.data.graph.bean.edge.*;
import com.dev.db.data.graph.bean.node.User;
import com.dev.db.data.graph.repository.node.UserRepository;
import com.dev.db.data.graph.service.inte.UserService;
import com.dev.db.data.sql.fmt.bean.SettingMst;
import com.dev.db.data.sql.fmt.repository.SettingMstRepository;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * Created by N5608296 on 14/09/17.
 */
@Service
public class UserData {
    private static final Logger logger = LoggerFactory.getLogger(UserData.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    SettingMstRepository settingMstRepository;

    public boolean syncSettingMasterData() {
        boolean status;
        try {
            for (SettingMst sm : settingMstRepository.findAll()) {
                User us = userRepository.findTop1ByUserId(String.valueOf(sm.getIndexId()));
                if (null != us) {
                    us.setNric(sm.getClntNric());
                    userRepository.save(us);
                }
            }
            status = true;
        } catch (Exception e) {
            status = false;
            logger.error("Service Error : ", e);
        }

        return status;
    }

}
