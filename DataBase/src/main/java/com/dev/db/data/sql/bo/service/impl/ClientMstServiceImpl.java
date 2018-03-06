package com.dev.db.data.sql.bo.service.impl;


import com.dev.db.data.sql.bo.bean.ClientMst;
import com.dev.db.data.sql.bo.repository.ClientMstRepository;
import com.dev.db.data.sql.bo.service.inte.ClientMstService;
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
public class ClientMstServiceImpl implements ClientMstService {
    private static final Logger logger = LoggerFactory.getLogger(ClientMstServiceImpl.class);
    @Autowired
    ClientMstRepository repository;


    @Override
    public JSONObject getAllClientDetails() {
        JSONArray result = new JSONArray();
        JSONObject main = new JSONObject();
        Gson gson = new Gson();
        for(ClientMst cm : repository.findAll()){
            result.add(cm);
        }

        main.put("result", gson.toJson(result));
        return main;

    }

}
