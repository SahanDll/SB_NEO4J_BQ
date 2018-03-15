package com.dev.ra.controller;

/**
 * Created by N5608296 on 03/01/2018 003.
 */

import com.dev.bq.request.BigQueryRequest;
import com.google.api.services.bigquery.model.GetQueryResultsResponse;
import org.json.simple.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/big-query")
public class BigQueryController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BigQueryController.class);
    /**
     * GET /read  --> Read all big query.
     */
    @RequestMapping(value = "/get-data", method = RequestMethod.GET)
    public JSONObject syncAndroidData(@RequestParam("platform") int platform,
                                                   @RequestParam("queryCode") int queryCode,
                                                   @RequestParam("backDates") int backDate,
                                                   @RequestParam("records") int records) {
        GetQueryResultsResponse response = null;
        JSONObject jsonObject = new JSONObject();
        try {
            if (platform == 1) {
                response = BigQueryRequest.getInstance().dispatchAndroidQueryRequest(queryCode, backDate, records);
            } else {
                response = BigQueryRequest.getInstance().dispatchIosQueryRequest(queryCode, backDate, records);
            }
            jsonObject.put("platform", platform);
            jsonObject.put("response", response);
        } catch (Exception e) {
            logger.error("Controller Error : ", e);
        }
        return jsonObject;
    }

}