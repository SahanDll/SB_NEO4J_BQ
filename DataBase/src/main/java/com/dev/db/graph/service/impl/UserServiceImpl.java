package com.dev.db.graph.service.impl;

import com.dev.db.graph.bean.edge.*;
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
    public JSONObject read(String userId) {
        JSONArray result = new JSONArray();
        JSONObject main = new JSONObject();
        try {
            Gson gson = new Gson();
/*            JSONArray using = new JSONArray();
            JSONArray login = new JSONArray();
            JSONArray downloaded = new JSONArray();
            JSONArray view = new JSONArray();
            JSONArray engage = new JSONArray();*/
            User us = userRepository.findTop1ByUserId(userId);
            if (null != us){
                if (null != us.getUsing()) {
                    for(Using ui : us.getUsing()){
                        /*ui.setUser(null);
                        using.add(ui);*/
                        JSONObject di = new JSONObject();
                        di.put("Id", ui.getDeviceInfo().getId());
                        di.put("userId", ui.getUserId());
                        di.put("familyType", "USING");
                        di.put("name", ui.getDeviceInfo().getMobileModelName());
                        di.put("vendor", "Sentinel");
                        di.put("model", "Using");
                        di.put("protocol", ui.getDeviceInfo().getId());
                        di.put("address", "192.168.0.1");
                        result.add(di);
                    }
                }
                if (null != us.getLogin()) {
                    for(Login li : us.getLogin()){
                        /*li.setUser(null);
                        login.add(li);*/
                        JSONObject gi = new JSONObject();
                        gi.put("Id", li.getGeoInfo().getId());
                        gi.put("userId", li.getUserId());
                        gi.put("familyType", "LOGIN");
                        gi.put("name", li.getGeoInfo().getCity());
                        gi.put("vendor", "Sentinel");
                        gi.put("model", "Login");
                        gi.put("protocol", li.getGeoInfo().getId());
                        gi.put("address", "192.168.0.1");
                        result.add(gi);
                    }
                }
                if (null != us.getDownloaded()) {
                    for(Downloaded dl : us.getDownloaded()){
                        /*dl.setUser(null);
                        downloaded.add(dl);*/
                        JSONObject ai = new JSONObject();
                        ai.put("Id", dl.getAppInfo().getId());
                        ai.put("userId", dl.getUserId());
                        ai.put("familyType", "DOWNLOAD");
                        ai.put("name", dl.getAppInfo().getAppVersion());
                        ai.put("vendor", "Sentinel");
                        ai.put("model", "Download");
                        ai.put("protocol", dl.getAppInfo().getId());
                        ai.put("address", "192.168.0.1");
                        result.add(ai);
                    }
                }
                if (null != us.getView()) {
                    for(View vi : us.getView()){
                        /*vi.setUser(null);
                        view.add(vi);*/
                        JSONObject st = new JSONObject();
                        st.put("Id", vi.getScreen().getId());
                        st.put("userId", vi.getUserId());
                        st.put("familyType", "VIEW");
                        st.put("name", vi.getScreen().getPreviousScreen()+" -> "+vi.getScreen().getScreen());
                        st.put("vendor", "Sentinel");
                        st.put("model", "View");
                        st.put("protocol", vi.getScreen().getId());
                        st.put("address", "192.168.0.1");
                        result.add(st);
                    }
                }
                if (null != us.getEngage()) {
                    for(Engage eg : us.getEngage()){
/*                        eg.setUser(null);
                        engage.add(eg);*/
                        JSONObject se = new JSONObject();
                        se.put("Id", eg.getScreen().getId());
                        se.put("userId", eg.getUserId());
                        se.put("familyType", "ENGAGE");
                        se.put("name", eg.getScreen().getScreen());
                        se.put("vendor", "Sentinel");
                        se.put("model", "Engage");
                        se.put("protocol", eg.getScreen().getId());
                        se.put("address", "192.168.0.1");
                        result.add(se);
                    }
                }
                JSONObject user = new JSONObject();
                user.put("Id", us.getId());
                user.put("userId", us.getUserId());
                user.put("familyType", "USER");
                user.put("name", us.getUserId());
                user.put("vendor", "Sentinel");
                user.put("model", "User");
                user.put("protocol", us.getUserId());
                user.put("address", "192.168.0.1");

/*                user.put("rel-using", using);
                user.put("rel-downloaded", downloaded);
                user.put("rel-login", login);
                user.put("rel-view", view);
                user.put("rel-engage", engage);*/

                result.add(user);
                main.put("Result", result);
            }
        } catch (Exception e) {
            logger.error("Error ", e);
        }
        return main;
    }

    @Override
    public Map<String, Object> update(User user) {
        return null;
    }

    @Override
    public JSONObject delete(String userId) {
        return null;
    }

    @Override
    public JSONObject readAll() {
        JSONArray result = new JSONArray();
        JSONObject main = new JSONObject();
        try {
            Gson gson = new Gson();
/*            List<Long> using = new ArrayList<>();
            List<Long> login = new ArrayList<>();
            List<Long> downloaded = new ArrayList<>();
            List<Long> view = new ArrayList<>();
            List<Long> engage = new ArrayList<>();*/
            JSONArray using = new JSONArray();
            JSONArray login = new JSONArray();
            JSONArray downloaded = new JSONArray();
            JSONArray view = new JSONArray();
            JSONArray engage = new JSONArray();
            for (User us : userRepository.findAll()) {
/*                if (null != us.getUsing()) {
                    for(Using ui : us.getUsing()){
                        ui.setUser(null);
                        using.add(ui);
                    }
                }
                if (null != us.getLogin()) {
                    for(Login li : us.getLogin()){
                        li.setUser(null);
                        login.add(li);
                    }
                }
                if (null != us.getDownloaded()) {
                    for(Downloaded dl : us.getDownloaded()){
                        dl.setUser(null);
                        downloaded.add(dl);
                    }
                }
                if (null != us.getView()) {
                    for(View vi : us.getView()){
                        vi.setUser(null);
                        view.add(vi);
                    }
                }
                if (null != us.getEngage()) {
                    for(Engage eg : us.getEngage()){
                        eg.setUser(null);
                        engage.add(eg);
                    }
                }*/
                JSONObject user = new JSONObject();
                user.put("Id", us.getId());
                user.put("userId", us.getUserId());
                user.put("familyType", us.getUserId());
                user.put("name", us.getUserId());
                user.put("vendor", "Sentinel");
                user.put("model", "UserAll");
                user.put("protocol", us.getUserId());
                user.put("address", "192.168.0.1");

/*                user.put("rel-using", using);
                user.put("rel-downloaded", downloaded);
                user.put("rel-login", login);
                user.put("rel-view", view);
                user.put("rel-engage", engage);*/

                //result.add(gson.toJson(user));
                result.add(user);
            }
            main.put("Result", result);
            main.put("RecordCount", result.size());
        } catch (Exception e) {
            logger.error("Error ", e);
        }
        return main;
    }
}
