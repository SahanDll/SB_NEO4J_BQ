package com.dev.ra.controller;

/**
 * Created by N5608296 on 03/01/2018 003.
 */

import com.dev.bq.request.BigQueryRequest;
import com.dev.db.data.graph.adapter.UsingAdapter;
import com.dev.db.data.graph.bean.edge.Using;
import com.dev.db.data.graph.service.inte.UsingService;
import com.google.api.services.bigquery.model.GetQueryResultsResponse;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/using")
public class UsingController {

    @Autowired
    UsingService usingService;
    @Value("${const.bq.backDate}")
    private int backDate;

    /**
     * GET /read  --> Read all popularSearch from the database.
     */
    @RequestMapping(value = "/sync-android-data", method = RequestMethod.GET)
    public GetQueryResultsResponse syncAndroidData() {
        GetQueryResultsResponse response = null;
        try {
            response = BigQueryRequest.getInstance().dispatchAndroidQueryRequest(2, backDate, 3);
            if (null != response) {
                for(Using ui: UsingAdapter.getInstance().syncData(response.getRows())){
                    ui.setPlatform("ANDROID");
                    System.out.println(usingService.create(ui));
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
            response = BigQueryRequest.getInstance().dispatchIosQueryRequest(2, backDate, 3);
            if (null != response) {
                for(Using ui: UsingAdapter.getInstance().syncData(response.getRows())){
                    ui.setPlatform("IOS");
                    System.out.println(usingService.create(ui));
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
            response = usingService.readAll();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}