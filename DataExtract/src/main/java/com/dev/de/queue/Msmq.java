package com.dev.de.queue;

import ionic.Msmq.Message;
import ionic.Msmq.MessageQueueException;
import ionic.Msmq.Queue;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.UnknownHostException;

public class Msmq {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Msmq.class);

    public static boolean createQueue(String qName, String ip) {
        boolean status;
        String fullName = "DIRECT=" + "TCP" + ":" + ip + "\\private$\\" + qName;
        String mLabel = "msmqProducer";
        boolean transactional = false;
        try {
            //logger.info("Machine IP : "+Inet4Address.getLocalHost().getHostAddress());
            Queue.create(fullName, mLabel, transactional);
            status = true;
            logger.info("Msmq : " + qName + " successfully created");
        } catch (Exception e) {
            status = false;
            logger.error("Msmq Error : ", e);
        }
        return status;
    }

    public static boolean deleteQueue(String qName, String ip) {
        boolean status;
        String fullName = "DIRECT=" + "TCP" + ":" + ip + "\\private$\\" + qName;
        try {
            Queue.delete(fullName);
            status = true;
            logger.info("Msmq : " + qName + " successfully deleted");
        } catch (Exception e) {
            status = false;
            logger.error("Msmq Error : ", e);
        }
        return status;
    }

    public static boolean addToQueue(String qName, String ip, JSONObject message) {
        boolean status;
        String correlationID = "L:none";
        String mLabel = "msmqProducer";
        String fullName = "DIRECT=" + "TCP" + ":" + ip + "\\private$\\" + qName;
        Queue queue;
        Message msg;
        try {
            queue = new Queue(fullName);
            msg = new Message(message.toString(), mLabel, correlationID);
            queue.send(msg);
            queue.close();
            status = true;
        } catch (Exception e) {
            logger.error("Msmq Error : ", e);
            status = false;
        }
        return status;
    }

    public static JSONArray getFromQueue(String qName, String ip, boolean readBytes) {
        JSONArray jsonArray = new JSONArray();
        String fullName = "DIRECT=" + "TCP" + ":" + ip + "\\private$\\" + qName;
        Queue queue;
        Message msg;
        String jString = null;
        try {
            queue = new Queue(fullName);
            if (null != queue) {
                while (null != queue.receive()) {
                    try {
                        msg = queue.receive();
                    } catch (Exception e) {
                        break;
                    }

                    try {
                        if (msg != null) {
                            if (readBytes) {
                                byte[] data = msg.getBody();
                                jString = new String(data);
                            }else{
                                jString = msg.getBodyAsString();
                            }
                        }
                    } catch (Exception e) {
                        continue;
                    }
                    jsonArray.add(jString);
                }
            }
            queue.close();
        } catch (Exception e) {
            logger.error("Msmq Error : ", e);
        }
        return jsonArray;
    }
}
