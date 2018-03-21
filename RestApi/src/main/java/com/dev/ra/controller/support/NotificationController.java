package com.dev.ra.controller.support;

import org.json.simple.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/pns")
public class NotificationController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public ResponseEntity<String> sendDefault(@RequestParam("nric") String nric) {

        return sendRequest(nric);
    }

    private ResponseEntity<String> sendRequest(String nric) {
        ResponseEntity<String> response = null;
        try {
            JSONObject jsonObject = new JSONObject();
            JSONObject data = new JSONObject();
            JSONObject value = new JSONObject();

            jsonObject.put("collapseKey", "mbbTrade");
            jsonObject.put("appId", "7");
            jsonObject.put("title", "<TEST> VOIR has hit the " + "lower" + " limit of your price alert");
            jsonObject.put("msg", "<TEST> VOIR has hit " + "lower" + " limit of your price alert, " + 5.75 + ", now trading at " + 5.67);
            value.put("entity", "0");
            value.put("alertId", "718828");
            value.put("alertType", "W");
            value.put("stockCode", "7240");
            value.put("contentId", "");
            value.put("newsUrl", "");
            data.put("data", value);
            jsonObject.put("additionalData", data);
            jsonObject.put("gcifIds", "N-"+nric);

            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
            Map<String, String> map = new HashMap<String, String>();
            map.put("Content-Type", "application/json");
            headers.setAll(map);
            disableSslVerification();
            HttpEntity<?> request = new HttpEntity<>(jsonObject, headers);
            logger.info("PNS Request : " + jsonObject.toJSONString());
            response = new RestTemplate().postForEntity("https://172.31.20.174:443/sendPush", request, String.class);
            logger.info("PNS Response : " + response);
        } catch (Exception e) {
            logger.error("Controller Error : ", e);
        }

        return response;
    }

    private static void disableSslVerification() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (NoSuchAlgorithmException e) {
            logger.error("", e);
        } catch (KeyManagementException e) {
            logger.error("", e);
        }
    }

}