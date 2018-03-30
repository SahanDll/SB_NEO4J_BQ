package com.dev.db.data.graph.adapter;

import com.dev.db.data.graph.bean.edge.Trade;
import com.dev.db.data.graph.bean.node.Stock;
import com.dev.db.data.graph.bean.node.User;
import com.dev.db.data.graph.model.NotificationClickUser;
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
                ncu.setTime(Long.valueOf(row.getF().get(4).get("v").toString()));
                System.out.println(ncu);
                list.add(ncu);

            } catch (Exception e) {
                //LOGGER.error("Adapter : ", e);
            }
        }
        return list;
    }

}
