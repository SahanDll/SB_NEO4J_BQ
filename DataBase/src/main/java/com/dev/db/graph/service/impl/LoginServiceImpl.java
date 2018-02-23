package com.dev.db.graph.service.impl;

import com.dev.db.graph.bean.edge.Login;
import com.dev.db.graph.bean.node.GeoInfo;
import com.dev.db.graph.bean.node.User;
import com.dev.db.graph.repository.edge.LoginRepository;
import com.dev.db.graph.repository.node.GeoInfoRepository;
import com.dev.db.graph.repository.node.UserRepository;
import com.dev.db.graph.service.inte.LoginService;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by N5608296 on 14/09/17.
 */
@Service
public class LoginServiceImpl implements LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    GeoInfoRepository geoInfoRepository;
    @Autowired
    LoginRepository loginRepository;

    @Override
    public Login create(Login login) {
        Login li = loginRepository.findTop1ByUserIdAndLocationAndPlatform(login.getUserId(), login.getLocation(), login.getPlatform());
        User us = userRepository.findTop1ByUserId(login.getUserId());
        GeoInfo gi = geoInfoRepository.findTop1ByCity(login.getLocation());
        if(null == li){
            if(null != us){
                login.getUser().setId(us.getId());
            }
            if(null != gi){
                login.getGeoInfo().setId(gi.getId());
            }
            login.setUser(userRepository.save(login.getUser()));
            login.setGeoInfo(geoInfoRepository.save(login.getGeoInfo()));
            return loginRepository.save(login);
        }else{
            login.getUser().setId(li.getUser().getId());
            li.setUser(login.getUser());
            login.getGeoInfo().setId(li.getGeoInfo().getId());
            li.setGeoInfo(login.getGeoInfo());
            if(!li.getLoginDate().contains(login.getLoginDate().get(0))){
                li.getLoginDate().add(login.getLoginDate().get(0));
            }
            return loginRepository.save(li);
        }

    }

    @Override
    public Map<String, Object> read(String loginId) {
        return null;
    }

    @Override
    public Map<String, Object> update(Login login) {
        return null;
    }

    @Override
    public Map<String, Object> delete(String loginId) {
        return null;
    }

    @Override
    public JSONObject readAll() {
        JSONArray result = new JSONArray();
        JSONObject main = new JSONObject();
        try {
            Gson gson = new Gson();
            List<Long> login = new ArrayList<>();
            for (Login lif : loginRepository.findAll()) {
                login.add(lif.getRelationshipId());
            }
            result.add(gson.toJson(login));
            main.put("Result", result);
            main.put("RecordCount", login.size());
        } catch (Exception e) {
            logger.error("Error ", e);
        }
        return main;
    }
}
