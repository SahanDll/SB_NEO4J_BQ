package com.dev.controller;

/**
 * Created by N5608296 on 03/01/2018 003.
 */

import com.dev.bigquery.BigQueryRequest;
import com.dev.db.graph.adapter.EngageAdapter;
import com.dev.db.graph.adapter.ViewAdapter;
import com.dev.db.graph.bean.edge.Engage;
import com.dev.db.graph.bean.edge.View;
import com.dev.db.graph.service.inte.EngageService;
import com.dev.db.graph.service.inte.ViewService;
import com.google.api.services.bigquery.model.GetQueryResultsResponse;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/engage")
public class EngageController {

    @Autowired
    EngageService engageService;
    @Value("${const.bq.backDate}")
    private int backDate;

    /**
     * GET /read  --> Read all popularSearch from the database.
     */
    @RequestMapping(value = "/sync-android-data", method = RequestMethod.GET)
    public GetQueryResultsResponse syncAndroidData() {
        GetQueryResultsResponse response = null;
        try {
            response = BigQueryRequest.getInstance().dispatchAndroidQueryRequest(10, backDate, 3);
            if (null != response) {
                for(Engage eg: EngageAdapter.getInstance().syncData(response.getRows())){
                    eg.setPlatform("ANDROID");
                    System.out.println(engageService.create(eg));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * GET /read  --> Read all popularSearch from the database.
     */
    @RequestMapping(value = "/sync-ios-data", method = RequestMethod.GET)
    public GetQueryResultsResponse syncIosData() {
        GetQueryResultsResponse response = null;
        try {
            response = BigQueryRequest.getInstance().dispatchIosQueryRequest(10, backDate, 3);
            if (null != response) {
                for(Engage eg: EngageAdapter.getInstance().syncData(response.getRows())){
                    eg.setPlatform("IOS");
                    System.out.println(engageService.create(eg));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * GET /read  --> Read all popularSearch from the database.
     */
    @RequestMapping(value = "/read-all", method = RequestMethod.GET)
    public JSONObject readAll() {
        JSONObject response = null;
        try {
            response = engageService.readAll();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}