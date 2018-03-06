package com.dev.au.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by N5608296 on 12/09/17.
 */
public class Common {
    private static final Logger logger = LoggerFactory.getLogger(Common.class);
    private static Common self;
    private String updatedUser;
    private String error = "ERROR : ";
    private static Map<String,String> userADMap;

    private Common(){
        /*Implementation not required*/
    }

    public static synchronized Common getInstance() {
        if (self == null) {
            self = new Common();
            userADMap = new HashMap<>();
        }
        return self;
    }

    public  boolean authenticateAD(String userName, String password){
        boolean result = false;
        DirContext ctx = null;
        try {
            Hashtable<String, String> env = new Hashtable<String, String>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldap://172.31.92.5:389");
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PRINCIPAL, "MAYBANK-MY\\"+userName);
            env.put(Context.SECURITY_CREDENTIALS, password);

            ctx = new InitialDirContext(env);
            result = ctx != null;

            if(ctx != null)
                ctx.close();
        }catch (Exception e) {
            result = false;
        }
        return result;
    }

    public String getUpdatedUser() {
        return this.updatedUser;
    }

    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Map<String, String> getUserADMap() {
        return userADMap;
    }


}
