package com.dev.ra.controller.common;

/**
 * Created by N5608296 on 03/01/2018 003.
 */

import com.dev.ds.sync.service.UserDataService;
import com.google.api.services.bigquery.model.GetQueryResultsResponse;
import org.json.simple.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/data-sync")
public class DataSyncController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DataSyncController.class);
    @Autowired
    UserDataService userDataService;
    /**
     * GET /sync  --> Sync data with fmt.
     */
    @RequestMapping(value = "/user-data", method = RequestMethod.GET)
    public JSONObject syncAndroidData() {
        GetQueryResultsResponse response = null;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("status", userDataService.syncSettingMasterData());
        } catch (Exception e) {
            logger.error("Controller Error : ", e);
        }
        return jsonObject;
    }

}