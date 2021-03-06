package com.dev.db.data.graph.service.impl;

import com.dev.db.data.graph.bean.edge.*;
import com.dev.db.data.graph.bean.node.User;
import com.dev.db.data.graph.model.NotificationClickUser;
import com.dev.db.data.graph.repository.node.UserRepository;
import com.dev.db.data.graph.service.inte.UserService;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
            for (User us : userRepository.findAll()) {

                JSONArray using = new JSONArray();
                JSONArray login = new JSONArray();
                JSONArray downloaded = new JSONArray();
                JSONArray view = new JSONArray();
                JSONArray engage = new JSONArray();

                if (null != us.getUsing()) {
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
                }
                JSONObject user = new JSONObject();
                user.put("Id", us.getId());
                user.put("userId", us.getUserId());
                //user.put("familyType", us.getUserId());
                //user.put("name", us.getUserId());
                //user.put("vendor", "Sentinel");
                //user.put("model", "UserAll");
                //user.put("protocol", us.getUserId());
                //user.put("address", "192.168.0.1");

                user.put("rel-using", using);
                user.put("rel-downloaded", downloaded);
                user.put("rel-login", login);
                user.put("rel-view", view);
                user.put("rel-engage", engage);

                //System.out.println(gson.toJson(user));
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

    @Override
    public User updateNotificationClick(NotificationClickUser ncu) {
        User us = userRepository.findTop1ByUserId(ncu.getUserId());
        if (null == us){
            us = new User();
            us.setUserId(ncu.getUserId());
            us.setFirstLogin(new Date());
        }

        switch (ncu.getType()){
            case "N":
                if(null == us.getNewsClick()){
                    us.setNewsClick(new ArrayList<>());
                }
                if(!us.getNewsClick().contains(ncu.getTime())){
                    us.getNewsClick().add(ncu.getTime());
                }
                break;
            case "W":
                if(null == us.getPriceClick()){
                    us.setPriceClick(new ArrayList<>());
                }
                if(!us.getPriceClick().contains(ncu.getTime())){
                    us.getPriceClick().add(ncu.getTime());
                }
                break;
            case "P":
                if(null == us.getPortfolioClick()){
                    us.setPortfolioClick(new ArrayList<>());
                }
                if(!us.getPortfolioClick().contains(ncu.getTime())){
                    us.getPortfolioClick().add(ncu.getTime());
                }
                break;
            case "C":
                if(null == us.getPaymentClick()){
                    us.setPaymentClick(new ArrayList<>());
                }
                if(!us.getPaymentClick().contains(ncu.getTime())){
                    us.getPaymentClick().add(ncu.getTime());
                }
                break;
            case "E":
                if(null == us.getEntitlementClick()){
                    us.setEntitlementClick(new ArrayList<>());
                }
                if(!us.getEntitlementClick().contains(ncu.getTime())){
                    us.getEntitlementClick().add(ncu.getTime());
                }
                break;
            case "I":
                if(null == us.getInsightClick()){
                    us.setInsightClick(new ArrayList<>());
                }
                if(!us.getInsightClick().contains(ncu.getTime())){
                    us.getInsightClick().add(ncu.getTime());
                }
                break;
            case "S":
                if(null == us.getSystemClick()){
                    us.setSystemClick(new ArrayList<>());
                }
                if(!us.getSystemClick().contains(ncu.getTime())){
                    us.getSystemClick().add(ncu.getTime());
                }
                break;
            case "J":
                if(null == us.getJillClick()){
                    us.setJillClick(new ArrayList<>());
                }
                if(!us.getJillClick().contains(ncu.getTime())){
                    us.getJillClick().add(ncu.getTime());
                }
                break;
            case "O":
                if(null == us.getOrderClick()){
                    us.setOrderClick(new ArrayList<>());
                }
                if(!us.getOrderClick().contains(ncu.getTime())){
                    us.getOrderClick().add(ncu.getTime());
                }
                break;
        }

        return userRepository.save(us);
    }
}
