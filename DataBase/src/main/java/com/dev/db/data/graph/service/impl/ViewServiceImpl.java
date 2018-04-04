package com.dev.db.data.graph.service.impl;

import com.dev.db.data.graph.bean.edge.View;
import com.dev.db.data.graph.bean.node.ScreenTransition;
import com.dev.db.data.graph.bean.node.User;
import com.dev.db.data.graph.repository.edge.ViewRepository;
import com.dev.db.data.graph.repository.node.ScreenTransitionRepository;
import com.dev.db.data.graph.repository.node.UserRepository;
import com.dev.db.data.graph.service.inte.ViewService;
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
public class ViewServiceImpl implements ViewService {
    private static final Logger logger = LoggerFactory.getLogger(ViewServiceImpl.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    ScreenTransitionRepository screenRepository;
    @Autowired
    ViewRepository viewRepository;


    @Override
    public View create(View view) {
        View vi = viewRepository.findTop1ByUserIdAndFromScreenNameAndToScreenNameAndPlatform(view.getUserId(), view.getFromScreenName(), view.getToScreenName(), view.getPlatform());
        User us = userRepository.findTop1ByUserId(view.getUserId());
        if(null == us){
            view.getUser().setFirstLogin(new Date());
        }
        ScreenTransition st = screenRepository.findTop1ByPreviousScreenAndScreen(view.getScreen().getPreviousScreen(), view.getScreen().getScreen());
        if(null == vi){
            if(null != us){
                view.getUser().setId(us.getId());
            }
            if(null != st){
                view.getScreen().setId(st.getId());
            }
            view.setUser(userRepository.save(view.getUser()));
            view.setScreen(screenRepository.save(view.getScreen()));
            return viewRepository.save(view);
        }else{
            view.getUser().setId(vi.getUser().getId());
            vi.setUser(view.getUser());
            view.getScreen().setId(vi.getScreen().getId());
            vi.setScreen(view.getScreen());
            if(!vi.getViewTime().contains(view.getViewTime().get(0))){
                vi.getViewTime().add(view.getViewTime().get(0));
            }

            return viewRepository.save(vi);
        }
    }

    @Override
    public Map<String, Object> read(String viewId) {
        return null;
    }

    @Override
    public Map<String, Object> update(View view) {
        return null;
    }

    @Override
    public Map<String, Object> delete(String viewId) {
        return null;
    }

    @Override
    public JSONObject readAll() {
        JSONArray result = new JSONArray();
        JSONObject main = new JSONObject();
        try {
            Gson gson = new Gson();
            List<Long> view = new ArrayList<>();
            for (View vi : viewRepository.findAll()) {
                view.add(vi.getRelationshipId());
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
