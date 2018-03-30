package com.dev.ra.controller.nodes;

/**
 * Created by N5608296 on 03/01/2018 003.
 */

import com.dev.bq.request.BigQueryRequest;
import com.dev.db.data.graph.adapter.NotificationAdapter;
import com.dev.db.data.graph.adapter.TradeAdapter;
import com.dev.db.data.graph.bean.edge.Trade;
import com.dev.db.data.graph.model.NotificationClickUser;
import com.dev.db.data.graph.service.inte.TradeService;
import com.dev.db.data.graph.service.inte.UserService;
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
@RequestMapping("/api/notification")
public class NotificationController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(NotificationController.class);
    @Autowired
    UserService userService;
    @Value("${const.bq.backDate}")
    private int backDate;

    /**
     * GET /read  --> Read all popularSearch from the database.
     */
    @RequestMapping(value = "/clicked/sync-android-data", method = RequestMethod.GET)
    public GetQueryResultsResponse syncAndroidClickedData() {
        GetQueryResultsResponse response = null;
        try {
            response = BigQueryRequest.getInstance().dispatchAndroidQueryRequest(14, backDate, 3);
            if (null != response) {
                for(NotificationClickUser ncu: NotificationAdapter.getInstance().syncClickedData(response.getRows())){
                    System.out.println(userService.updateNotificationClick(ncu));
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
    @RequestMapping(value = "/clicked/sync-ios-data", method = RequestMethod.GET)
    public GetQueryResultsResponse syncIosClickedData() {
        GetQueryResultsResponse response = null;
        try {
            response = BigQueryRequest.getInstance().dispatchIosQueryRequest(14, backDate, 3);
            if (null != response) {
                for(NotificationClickUser ncu: NotificationAdapter.getInstance().syncClickedData(response.getRows())){
                    System.out.println(userService.updateNotificationClick(ncu));
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
            response = userService.readAll();

        } catch (Exception e) {
            logger.error("Controller Error : ", e);
        }
        return response;
    }
}