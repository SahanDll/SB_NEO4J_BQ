package com.dev.ds.sync.fetch;

import com.dev.bq.util.Common;
import com.dev.ds.sync.service.UserData;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
@Component
public class UserDataFetch {
    private static final Logger logger = LoggerFactory.getLogger(UserDataFetch.class);
    @Autowired
    UserData userDataService;
    @Scheduled(fixedRate=10000)
    public void fetchSingleData(){
/*        String apiUrl = "http://localhost:8381/fmt-sentinel/api/data-sync/user-data";
        RestTemplate restTemplate = new RestTemplate();
        try {
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
            Map<String,String> map = new HashMap<String, String>();
            map.put("Content-Type", "application/json");
            map.put("Authorization", Common.authenticate());
            headers.setAll(map);
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity<JSONObject> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, JSONObject.class);
            logger.info("Result - status ("+ response.getStatusCode() + ") has body: " + response.hasBody()+" -> "+response.getBody());

        }
        catch (Exception e) {
            logger.error("Exception: "+ e.getMessage());
        }*/

        try {
            //userDataService.syncSettingMasterData();
            System.out.println(userDataService.getSettingMasterByIdAndNric((long) 3, "N123452"));
        } catch (Exception e) {
            logger.error("Exception: "+ e.getMessage());
        }
    }
}
