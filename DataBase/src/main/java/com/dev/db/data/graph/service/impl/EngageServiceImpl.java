package com.dev.db.data.graph.service.impl;

import com.dev.db.data.graph.bean.edge.Engage;
import com.dev.db.data.graph.bean.node.ScreenEngagement;
import com.dev.db.data.graph.bean.node.User;
import com.dev.db.data.graph.repository.edge.EngageRepository;
import com.dev.db.data.graph.repository.node.ScreenEngagementRepository;
import com.dev.db.data.graph.repository.node.UserRepository;
import com.dev.db.data.graph.service.inte.EngageService;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by N5608296 on 14/09/17.
 */
@Service
public class EngageServiceImpl implements EngageService {
    private static final Logger logger = LoggerFactory.getLogger(EngageServiceImpl.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    ScreenEngagementRepository screenEngagementRepository;
    @Autowired
    EngageRepository engageRepository;


    @Override
    public Engage create(Engage engage) {
        Engage eg = engageRepository.findTop1ByUserIdAndScreenNameAndPlatform(engage.getUserId(), engage.getScreenName(), engage.getPlatform());
        User us = userRepository.findTop1ByUserId(engage.getUserId());
        if(null == us){
            engage.getUser().setFirstLogin(new Date());
        }
        ScreenEngagement se = screenEngagementRepository.findTop1ByScreen(engage.getScreenName());
        if(null == eg){
            if(null != us){
                engage.getUser().setId(us.getId());
            }
            if(null != se){
                engage.getScreen().setId(se.getId());
            }
            engage.setUser(userRepository.save(engage.getUser()));
            engage.setScreen(screenEngagementRepository.save(engage.getScreen()));
            return engageRepository.save(engage);
        }else{
            engage.getUser().setId(eg.getUser().getId());
            eg.setUser(engage.getUser());
            engage.getScreen().setId(eg.getScreen().getId());
            eg.setScreen(engage.getScreen());
            if(!eg.getViewTime().contains(engage.getScreen().getViewTime())){
                eg.getViewTime().add(engage.getScreen().getViewTime());
                eg.getViewLength().add(engage.getScreen().getViewLength());
            }

            return engageRepository.save(eg);
        }
    }

    @Override
    public Map<String, Object> read(String engageId) {
        return null;
    }

    @Override
    public Map<String, Object> update(Engage engage) {
        return null;
    }

    @Override
    public Map<String, Object> delete(String engageId) {
        return null;
    }

    @Override
    public JSONObject readAll() {
        JSONArray result = new JSONArray();
        JSONObject main = new JSONObject();
        try {
            Gson gson = new Gson();
            List<Long> view = new ArrayList<>();
            for (Engage eg : engageRepository.findAll()) {
                view.add(eg.getRelationshipId());
            }
            result.add(gson.toJson(view));
            main.put("Result", result);
            main.put("RecordCount", view.size());
        } catch (Exception e) {
            logger.error("Error ", e);
        }
        return main;
    }
}
