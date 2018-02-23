package com.dev.db.graph.service.impl;

import com.dev.db.graph.bean.edge.Downloaded;
import com.dev.db.graph.bean.edge.Login;
import com.dev.db.graph.bean.edge.Using;
import com.dev.db.graph.bean.edge.View;
import com.dev.db.graph.bean.node.User;
import com.dev.db.graph.repository.node.UserRepository;
import com.dev.db.graph.service.inte.UserService;
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
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    UserRepository userRepository;


    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public Map<String, Object> read(String userId) {
        return null;
    }

    @Override
    public Map<String, Object> update(User user) {
        return null;
    }

    @Override
    public Map<String, Object> delete(String userId) {
        return null;
    }

    @Override
    public JSONObject readAll() {
        JSONArray result = new JSONArray();
        JSONObject main = new JSONObject();
        try {
            Gson gson = new Gson();
            List<Long> using = new ArrayList<>();
            List<Long> login = new ArrayList<>();
            List<Long> downloaded = new ArrayList<>();
            List<Long> view = new ArrayList<>();
            for (User us : userRepository.findAll()) {
                if (null != us.getUsing()) {
                    for(Using ui : us.getUsing()){
                        using.add(ui.getRelationshipId());
                    }
                }
                if (null != us.getLogin()) {
                    for(Login li : us.getLogin()){
                        login.add(li.getRelationshipId());
                    }
                }
                if (null != us.getDownloaded()) {
                    for(Downloaded dl : us.getDownloaded()){
                        downloaded.add(dl.getRelationshipId());
                    }
                }
                if (null != us.getView()) {
                    for(View vi : us.getView()){
                        view.add(vi.getRelationshipId());
                    }
                }
                JSONObject user = new JSONObject();
                user.put("Id", us.getId());
                user.put("userId", us.getUserId());
                user.put("rel-using", using);
                user.put("rel-downloaded", downloaded);
                user.put("rel-login", login);
                user.put("rel-view", view);

                result.add(gson.toJson(user));
            }
            main.put("Result", result);
            main.put("RecordCount", result.size());
        } catch (Exception e) {
            logger.error("Error ", e);
        }
        return main;
    }
}
