package com.dev.ra.controller.nodes;

/**
 * Created by N5608296 on 03/01/2018 003.
 */

import com.dev.bq.request.BigQueryRequest;
import com.dev.db.data.graph.adapter.EngageAdapter;
import com.dev.db.data.graph.bean.edge.Engage;
import com.dev.db.data.graph.service.inte.EngageService;
import com.google.api.services.bigquery.model.GetQueryResultsResponse;
import org.json.simple.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/engage")
public class EngageController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EngageController.class);
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
            logger.error("Controller Error : ", e);
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
            logger.error("Controller Error : ", e);
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
            logger.error("Controller Error : ", e);
        }
        return response;
    }
}