package com.dev.db.graph.service.impl;

import com.dev.db.graph.bean.edge.Using;
import com.dev.db.graph.bean.node.DeviceInfo;
import com.dev.db.graph.bean.node.User;
import com.dev.db.graph.repository.edge.UsingRepository;
import com.dev.db.graph.repository.node.DeviceInfoRepository;
import com.dev.db.graph.repository.node.UserRepository;
import com.dev.db.graph.service.inte.UsingService;
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
public class UsingServiceImpl implements UsingService {
    private static final Logger logger = LoggerFactory.getLogger(UsingServiceImpl.class);
    @Autowired
    DeviceInfoRepository deviceInfoRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UsingRepository usingRepository;


    @Override
    public Using create(Using using) {
        Using ui = usingRepository.findTop1ByUserIdAndDeviceAndPlatform(using.getUserId(), using.getDevice(), using.getPlatform());
        User us = userRepository.findTop1ByUserId(using.getUserId());
        DeviceInfo di = deviceInfoRepository.findTop1ByDeviceModel(using.getDevice());
        if(null == ui){
            if(null != us){
                using.getUser().setId(us.getId());
            }
            if(null != di){
                using.getDeviceInfo().setId(di.getId());
            }
            using.setUser(userRepository.save(using.getUser()));
            using.setDeviceInfo(deviceInfoRepository.save(using.getDeviceInfo()));
            return usingRepository.save(using);
        }else{
            using.getUser().setId(ui.getUser().getId());
            ui.setUser(using.getUser());
            using.getDeviceInfo().setId(ui.getDeviceInfo().getId());
            ui.setDeviceInfo(using.getDeviceInfo());
            return usingRepository.save(ui);
        }
    }

    @Override
    public Map<String, Object> read(String usingId) {
        return null;
    }

    @Override
    public Map<String, Object> update(Using using) {
        return null;
    }

    @Override
    public Map<String, Object> delete(String usingId) {
        return null;
    }

    @Override
    public JSONObject readAll() {
        JSONArray result = new JSONArray();
        JSONObject main = new JSONObject();
        try {
            Gson gson = new Gson();
            List<Long> using = new ArrayList<>();
            for (Using ui : usingRepository.findAll()) {
                using.add(ui.getRelationshipId());
            }
            result.add(gson.toJson(using));
            main.put("Result", result);
            main.put("RecordCount", using.size());
        } catch (Exception e) {
            logger.error("Error ", e);
        }
        return main;
    }
}
