package com.dev.bq.util;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Created by N5608296 on 14/02/2018 014.
 */
public class Common {
    private static final Logger LOGGER = LoggerFactory.getLogger(Common.class);
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    public static String getDatePortion(int date) {
        String datePortion = null;
        Calendar calendar = null;
        try {
            calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, date);
            datePortion = sdf.format(calendar.getTime());
        } catch (Exception e) {
            LOGGER.error("{\"error\" : \"" + e.getMessage() + "\"}");
        }
        return datePortion;
    }

    public static Date getBackDate(int date) {
        Calendar calendar = null;
        try {
            calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, date);
        } catch (Exception e) {
            LOGGER.error("{\"error\" : \"" + e.getMessage() + "\"}");
        }
        return calendar.getTime();
    }

    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return localDateTimeToDate(endOfDay);
    }

    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return localDateTimeToDate(startOfDay);
    }

    private static Date localDateTimeToDate(LocalDateTime startOfDay) {
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    private static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
    }

    public static String authenticate(){
        String accessToken = null;
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map<String,String> map = new HashMap<String, String>();
        map.put("Content-Type", "application/x-www-form-urlencoded");
        map.put("Authorization", baseAuthentication());
        headers.setAll(map);

        String url = "http://localhost:8381/fmt-sentinel/oauth/token";

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                .queryParam("grant_type","password")
                .queryParam("username","maybank")
                .queryParam("password",login());


        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<JSONObject> response = new RestTemplate().postForEntity(builder.toUriString(), request, JSONObject.class);

        if(null == response.getBody()){
            return accessToken;
        } else{
            try {
                accessToken = response.getBody().get("access_token").toString();
            } catch (Exception e) {
                LOGGER.error("Common Error : ", e);
            }
        }
        return  "Bearer "+ accessToken;
    }

    private static String login(){
        String pass = null;
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map<String,String> map = new HashMap<String, String>();
        map.put("Content-Type", "application/json");
        map.put("Authorization", baseAuthentication());
        headers.setAll(map);

        String url = "http://localhost:8381/fmt-sentinel/api/user-login/authenticateUser";

        JSONObject user = new JSONObject();
        user.put("userName", "maybank");
        user.put("encPassword", passwordEncrypt("secret@123"));

        HttpEntity<?> request = new HttpEntity<>(user, headers);
        ResponseEntity<JSONObject> response = new RestTemplate().postForEntity(url, request, JSONObject.class);

        if(null == response.getBody()){
            return pass;
        } else{
            try {
                if (Boolean.parseBoolean(response.getBody().get("auth").toString())) {
                    pass = response.getBody().get("pass").toString();
                }
            } catch (Exception e) {
                LOGGER.error("Common Error : ", e);
            }
        }
        return  pass;
    }

    private static String passwordEncrypt(String password){
        byte[] encKey = null;
        String encPassword = null;
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map<String,String> map = new HashMap<String, String>();
        map.put("Content-Type", "application/json");
        headers.setAll(map);

        ResponseEntity<JSONObject> response = new RestTemplate().getForEntity("http://localhost:8381/fmt-sentinel/api/user-login/getPublicKey", JSONObject.class);

        if(null == response.getBody()){
            return encPassword;
        } else{
            try {
                encKey = java.util.Base64.getDecoder().decode(response.getBody().get("key").toString());
                encPassword = com.dev.au.util.KeyGenerator.encryptWithExternalKey(encKey, password.getBytes());
            } catch (Exception e) {
                LOGGER.error("Common Error : ", e);
            }
        }
        return encPassword;
    }

    public static String baseAuthentication(){
        StringBuilder stringBuilder = new StringBuilder();
        String mimeEncodedString = null;
        String clientId = "sentinelJwtClientId";
        String clientSecret = "mySecret";
        try {
            stringBuilder.append(clientId + ':' + clientSecret);

            byte[] mimeBytes = stringBuilder.toString().getBytes("utf-8");
            mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Common Error : ", e);
        }
        return "Basic " +mimeEncodedString;
    }
}
