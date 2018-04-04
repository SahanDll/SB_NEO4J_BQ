package com.dev.db.data.graph.adapter;

import com.dev.db.data.graph.bean.edge.Open;
import com.dev.db.data.graph.bean.edge.Trade;
import com.dev.db.data.graph.bean.node.Notification;
import com.dev.db.data.graph.bean.node.Stock;
import com.dev.db.data.graph.bean.node.User;
import com.dev.db.data.graph.model.NotificationClickUser;
import com.dev.db.util.Common;
import com.google.api.client.util.Data;
import com.google.api.services.bigquery.model.TableRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class NotificationAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationAdapter.class);
    private static NotificationAdapter self;

    private NotificationAdapter(){
    }

    public static synchronized NotificationAdapter getInstance() {
        if (self == null) {
            self = new NotificationAdapter();
        }
        return self;
    }

    public List<NotificationClickUser> syncClickedData(List<TableRow> rows) {
        List<NotificationClickUser> list = new ArrayList<>();
        if(null == rows)
            return list;
        NotificationClickUser ncu = null;
        for (TableRow row : rows) {
            try {
                if(Data.isNull(row.getF().get(0).get("v")) || Data.isNull(row.getF().get(4).get("v"))){
                    continue;
                }
                ncu = new NotificationClickUser();
                ncu.setUserId(row.getF().get(0).get("v").toString());
                ncu.setType(row.getF().get(3).get("v").toString());
                ncu.setTime(Long.valueOf(row.getF().get(4).get("v").toString())/1000);
                list.add(ncu);

            } catch (Exception e) {
                LOGGER.error("Adapter : ", e);
            }
        }
        return list;
    }

    public List<Open> syncOpenData(List<TableRow> rows) {
        List<Open> list = new ArrayList<>();
        if(null == rows)
            return list;
        User us = null;
        Notification nt = null;
        Open op = null;
        long recordTime = 0;
        boolean newRecord = false;
        for (TableRow row : rows) {
            try {
                if(Data.isNull(row.getF().get(0).get("v")) || Data.isNull(row.getF().get(4).get("v"))){
                    continue;
                }

                if (recordTime != Long.parseLong(row.getF().get(4).get("v").toString())){
                    newRecord = true;
                    recordTime = Long.parseLong(row.getF().get(4).get("v").toString());
                    if(null != op){
                        list.add(op);
                    }
                    op = null;
                }else{
                    newRecord = false;
                }

                if (newRecord){
                    nt = new Notification();
                    us = new User();
                    us.setUserId(row.getF().get(0).get("v").toString());
                    op = new Open(us, nt);
                    op.setUserId(us.getUserId());
                    op.setOpenTime(new Date(recordTime/1000));
                    op.setCreateDate(Common.getCurrentDate());
                }
                if(!Data.isNull(row.getF().get(2).get("v"))) {
                    switch (row.getF().get(2).get("v").toString()) {
                        case "Notification_Type":
                            op.setNotificationType(row.getF().get(3).get("v").toString());
                            nt.setType(row.getF().get(3).get("v").toString());
                            break;
                        case "Screen_Name":
                            op.setScreen(row.getF().get(3).get("v").toString());
                            break;
                        case "Alert_Id":
                            op.setAlertId(row.getF().get(3).get("v").toString());
                            break;
                        default:
                    }
                }
            } catch (Exception e) {
                //LOGGER.error("Adapter : ", e);
            }
        }
        return list;
    }

}
