package com.dev.bigquery;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by N5608296 on 08/01/2018 008.
 */
@Service
public class BigQueryDataFetch {

    //@Scheduled(fixedRate=10000)
    //@Scheduled(fixedDelayString = "${const.bq.delay}", initialDelay = 1000L)
    public void fetchData() throws Exception {
        RestAssured.baseURI ="http://localhost:8381/fmt-sentinel/api/";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
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
        System.exit(0);
    }

}
