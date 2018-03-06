package com.dev.bq.fetch;

import com.dev.bq.util.Common;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by N5608296 on 08/01/2018 008.
 */
@Service
public class BigQueryDataFetch {


    @Scheduled(fixedDelayString = "${const.bq.delay}", initialDelay = 1000L)
    public void fetchData() throws Exception {
        RestAssured.baseURI ="http://localhost:8381/fmt-sentinel/api/";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", Common.authenticate());
        Response responseA1 = request.get("login/sync-android-data");
        TimeUnit.SECONDS.sleep(5);
        Response responseI1 = request.get("login/sync-ios-data");
        TimeUnit.SECONDS.sleep(5);
        Response responseA2 = request.get("using/sync-android-data");
        TimeUnit.SECONDS.sleep(5);
        Response responseI2 = request.get("using/sync-ios-data");
        TimeUnit.SECONDS.sleep(5);
        Response responseA3 = request.get("downloaded/sync-android-data");
        TimeUnit.SECONDS.sleep(5);
        Response responseI3 = request.get("downloaded/sync-ios-data");
        TimeUnit.SECONDS.sleep(5);
        Response responseA4 = request.get("view/sync-android-data");
        TimeUnit.SECONDS.sleep(5);
        Response responseI4 = request.get("view/sync-ios-data");
        TimeUnit.SECONDS.sleep(5);
        Response responseA5 = request.get("engage/sync-android-data");
        TimeUnit.SECONDS.sleep(5);
        Response responseI5 = request.get("engage/sync-ios-data");
        TimeUnit.SECONDS.sleep(5);
        System.exit(0);
    }



    //@Scheduled(fixedRate=10000)
    public void fetchSingleData(){
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map<String,String> map = new HashMap<String, String>();
        map.put("Content-Type", "application/json");
        headers.setAll(map);

/*        HttpEntity<?> request = new HttpEntity<>(jsonObject, headers);
        LOGGER.info("Request : " + jsonObject.toJSONString());
        ResponseEntity<String> response = new RestTemplate().postForEntity(url, request, String.class);*/
        ResponseEntity<String> response = new RestTemplate().getForEntity("http://localhost:8381/fmt-sentinel/api/user/read-all", String.class);
        System.out.println("Response : " +response.getBody());
    }
}
