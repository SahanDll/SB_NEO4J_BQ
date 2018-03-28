package com.dev.ra.controller.nodes;

/**
 * Created by N5608296 on 03/01/2018 003.
 */

import com.dev.bq.request.BigQueryRequest;
import com.dev.db.data.graph.adapter.TradeAdapter;
import com.dev.db.data.graph.adapter.ViewAdapter;
import com.dev.db.data.graph.bean.edge.Trade;
import com.dev.db.data.graph.bean.edge.View;
import com.dev.db.data.graph.service.inte.TradeService;
import com.dev.db.data.graph.service.inte.ViewService;
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
@RequestMapping("/api/trade")
public class TradeController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TradeController.class);
    @Autowired
    TradeService tradeService;
    @Value("${const.bq.backDate}")
    private int backDate;

    /**
     * GET /read  --> Read all popularSearch from the database.
     */
    @RequestMapping(value = "/sync-android-data", method = RequestMethod.GET)
    public GetQueryResultsResponse syncAndroidData() {
        GetQueryResultsResponse response = null;
        try {
            response = BigQueryRequest.getInstance().dispatchAndroidQueryRequest(12, backDate, 3);
            if (null != response) {
                for(Trade trd: TradeAdapter.getInstance().syncData(response.getRows())){
                    System.out.println(tradeService.create(trd));
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
            response = BigQueryRequest.getInstance().dispatchIosQueryRequest(12, backDate, 3);
            if (null != response) {
                for(Trade trd: TradeAdapter.getInstance().syncData(response.getRows())){
                    System.out.println(tradeService.create(trd));
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
            response = tradeService.readAll();

        } catch (Exception e) {
            logger.error("Controller Error : ", e);
        }
        return response;
    }
}