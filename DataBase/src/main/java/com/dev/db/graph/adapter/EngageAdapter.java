package com.dev.db.graph.adapter;

import com.dev.db.graph.bean.edge.Engage;
import com.dev.db.graph.bean.edge.View;
import com.dev.db.graph.bean.node.ScreenEngagement;
import com.dev.db.graph.bean.node.ScreenTransition;
import com.dev.db.graph.bean.node.User;
import com.dev.util.Common;
import com.google.api.client.util.Data;
import com.google.api.services.bigquery.model.TableRow;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EngageAdapter {
    private static final Logger LOGGER = Logger.getLogger(EngageAdapter.class);
    private static EngageAdapter self;

    private EngageAdapter(){
    }

    public static synchronized EngageAdapter getInstance() {
        if (self == null) {
            self = new EngageAdapter();
        }
        return self;
    }

    public List<Engage> syncData(List<TableRow> rows) {
        List<Engage> list = new ArrayList<>();
        if(null == rows)
            return list;
        User us = null;
        ScreenEngagement se = null;
        for (TableRow row : rows) {
            if(Data.isNull(row.getF().get(0).get("v")) || Data.isNull(row.getF().get(4).get("v"))){
                continue;
            }
            if((!Data.isNull(row.getF().get(3).get("v")) || !Data.isNull(row.getF().get(5).get("v")))){
                if("firebase_screen".equalsIgnoreCase(row.getF().get(2).get("v").toString())){
                    se = new ScreenEngagement();
                    se.setScreen(Data.isNull(row.getF().get(5).get("v")) ? "null" : row.getF().get(5).get("v").toString());
                }
                if("engagement_time_msec".equalsIgnoreCase(row.getF().get(2).get("v").toString())){
                    if (null != se) {
                        us = new User();
                        us.setUserId(row.getF().get(0).get("v").toString());

                        se.setViewTime(Long.parseLong(row.getF().get(4).get("v").toString()));
                        se.setViewLength(Long.parseLong(row.getF().get(3).get("v").toString()));

                        Engage eg = new Engage(us, se);
                        eg.setUserId(row.getF().get(0).get("v").toString());
                        eg.getViewTime().add(se.getViewTime());
                        eg.getViewLength().add(se.getViewLength());
                        eg.setScreenName(se.getScreen());
                        eg.setCreateDate(Common.getBackDate(-1));
                        list.add(eg);
                        se = null;
                    }
                }
            }
        }
        return list;
    }

}
