package com.dev.de.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

/**
 * Created by N5608296 on 12/5/16.
 */
public class IConstants {

    //sit uat
    public static final String KEYSTORE= "C:\\sentinel_data\\keystore.p12";
    public static final String KEYSTORE_PASS= "sahandevakalokuge";
    public static final String KEYSTORE_TYPE= "PKCS12";
    public static final String TRUSTSTORE = "C:\\sentinel_data\\truststore.jks";
    public static final String TRUSTSTORE_PASS = "sahandevakalokuge";
    public static final String TRUSTSTORE_TYPE = "JKS";
    //public static final String NIFI_URL = "https://172.30.2.103:8080/nifi";//sit
    public static final String NIFI_URL = "http://172.30.2.103:8181/nifi";//uat

    //prod
/*    public static final String KEYSTORE= "C:\\storm_data\\keystore.p12";
    public static final String KEYSTORE_PASS= "Maybank1234";
    public static final String KEYSTORE_TYPE= "PKCS12";
    public static final String TRUSTSTORE = "C:\\storm_data\\truststore.jks";
    public static final String TRUSTSTORE_PASS = "Maybank1234";
    public static final String TRUSTSTORE_TYPE = "JKS";
    public static final String NIFI_URL = "http://localhost:8080/nifi";*/

}
