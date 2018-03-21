package com.dev.ra.controller.common;


import com.dev.au.service.UserLoginService;
import com.dev.au.util.KeyGenerator;
import com.dev.db.model.AuthUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by N5608296 on 10/08/2017 010.
 */
@Controller
@RequestMapping("/api/user-login")
@Api(value="UserLogin", description="Operations related to user login")
public class UserLoginController {
    private static final Logger logger = LoggerFactory.getLogger(UserLoginController.class);
    @Autowired
    UserLoginService service;

    @ApiOperation(value = "Authenticate AuthUser", response = JSONObject.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully registered the user"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
    }
    )
    @RequestMapping(value = "authenticateUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JSONObject authenticateUser(@RequestBody AuthUser user) throws Exception {
        JSONObject jsonOut = null;
        System.out.println(user);
        user.setPassword(new String(KeyGenerator.getInstance().decrypt(Base64.getDecoder().decode(user.getEncPassword()))));
        System.out.println(user);
        jsonOut = service.authenticateUser(user);

        logger.info("Output : " + jsonOut.toString());
        return jsonOut;
    }

    @ApiOperation(value = "provide public key", response = JSONObject.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully registered the user"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
    }
    )
    @RequestMapping(value = "getPublicKey", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JSONObject getPublicKey() throws Exception {
        JSONObject jsonOut = new JSONObject();
        jsonOut.put("key", KeyGenerator.getInstance().getPublicKey());

        logger.info("Output : " + jsonOut.toString());
        return jsonOut;
    }

    @ApiOperation(value = "get base auth", response = JSONObject.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully registered the user"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
    }
    )
    @RequestMapping(value = "auth-base", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JSONObject getBase() throws Exception {
        JSONObject jsonOut = new JSONObject();
        jsonOut.put("base", com.dev.bq.util.Common.baseAuthentication());

        logger.info("Output : " + jsonOut.toString());
        return jsonOut;
    }

    @ApiOperation(value = "password encryption", response = JSONObject.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully registered the user"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
    }
    )
    @RequestMapping(value = "encrypt-password", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JSONObject getEncryptedPassword(@RequestParam("password") String password) throws Exception {
        JSONObject jsonOut = new JSONObject();
        String encPass = Base64.getEncoder().encodeToString(KeyGenerator.getInstance().encrypt(password.getBytes()));
        jsonOut.put("encPassword", encPass);

        logger.info("Output : " + jsonOut.toString());
        return jsonOut;
    }

    @ApiOperation(value = "get Token", response = JSONObject.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully registered the user"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
    }
    )
    @RequestMapping(value = "auth-token", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JSONObject getToken(@RequestParam("username") String username, @RequestParam("password") String password) throws Exception {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map<String,String> map = new HashMap<String, String>();
        map.put("Authorization", com.dev.bq.util.Common.baseAuthentication());
        map.put("Content-Type", "application/x-www-form-urlencoded");
        headers.setAll(map);

        String data = "grant_type=password&username=" + username + "&password=" + password;
        HttpEntity<?> request = new HttpEntity<>(data, headers);
        ResponseEntity<JSONObject> response = new RestTemplate().postForEntity("http://localhost:8381/fmt-sentinel/oauth/token", request, JSONObject.class);
        System.out.println("Response : " +response.getBody());

        logger.info("Output : " + response.getBody());
        return response.getBody();
    }
}
