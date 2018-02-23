package com.dev.db.graph.service.impl;

import com.dev.db.graph.bean.edge.Downloaded;
import com.dev.db.graph.bean.node.AppInfo;
import com.dev.db.graph.bean.node.User;
import com.dev.db.graph.repository.edge.DownloadedRepository;
import com.dev.db.graph.repository.node.AppInfoRepository;
import com.dev.db.graph.repository.node.UserRepository;
import com.dev.db.graph.service.inte.DownloadedService;
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
public class DownloadedServiceImpl implements DownloadedService {
    private static final Logger logger = LoggerFactory.getLogger(DownloadedServiceImpl.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    AppInfoRepository appInfoRepository;
    @Autowired
    DownloadedRepository downloadedRepository;


    @Override
    public Downloaded create(Downloaded downloaded) {
        Downloaded dl = downloadedRepository.findTop1ByUserIdAndVersionAndPlatform(downloaded.getUserId(), downloaded.getVersion(), downloaded.getPlatform());
        User us = userRepository.findTop1ByUserId(downloaded.getUserId());
        AppInfo ai = appInfoRepository.findTop1ByAppVersion(downloaded.getVersion());
        if(null == dl){
            if(null != us){
                downloaded.getUser().setId(us.getId());
            }
            if(null != ai){
                downloaded.getAppInfo().setId(ai.getId());
            }
            downloaded.setUser(userRepository.save(downloaded.getUser()));
            downloaded.setAppInfo(appInfoRepository.save(downloaded.getAppInfo()));
            return downloadedRepository.save(downloaded);
        }else{
            downloaded.getUser().setId(dl.getUser().getId());
            dl.setUser(downloaded.getUser());
            downloaded.getAppInfo().setId(dl.getAppInfo().getId());
            dl.setAppInfo(downloaded.getAppInfo());
            return downloadedRepository.save(dl);
        }
    }

    @Override
    public Map<String, Object> read(String downloadedId) {
        return null;
    }

    @Override
    public Map<String, Object> update(Downloaded downloaded) {
        return null;
    }

    @Override
    public Map<String, Object> delete(String downloadedId) {
        return null;
    }

    @Override
    public JSONObject readAll() {
        JSONArray result = new JSONArray();
        JSONObject main = new JSONObject();
        try {
            Gson gson = new Gson();
            List<Long> downloaded = new ArrayList<>();
            for (Downloaded dl : downloadedRepository.findAll()) {
                downloaded.add(dl.getRelationshipId());
            }
            result.add(gson.toJson(downloaded));
            main.put("Result", result);
            main.put("RecordCount", downloaded.size());
        } catch (Exception e) {
            logger.error("Error ", e);
        }
        return main;
    }
}
