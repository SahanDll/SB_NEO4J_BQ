package com.dev.db.graph.adapter;

import com.dev.db.graph.bean.edge.Downloaded;
import com.dev.db.graph.bean.node.AppInfo;
import com.dev.db.graph.bean.node.User;
import com.dev.util.Common;
import com.google.api.client.util.Data;
import com.google.api.services.bigquery.model.TableRow;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DownloadedAdapter {
    private static final Logger LOGGER = Logger.getLogger(DownloadedAdapter.class);
    private static DownloadedAdapter self;

    private DownloadedAdapter(){
    }

    public static synchronized DownloadedAdapter getInstance() {
        if (self == null) {
            self = new DownloadedAdapter();
        }
        return self;
    }

    public List<Downloaded> syncData(List<TableRow> rows) {
        List<Downloaded> list = new ArrayList<>();
        if(null == rows)
            return list;
        for (TableRow row : rows) {
            if(Data.isNull(row.getF().get(0).get("v"))){
                continue;
            }

            User us = new User();
            us.setUserId(row.getF().get(0).get("v").toString());

            AppInfo ai = new AppInfo();
            ai.setAppVersion(Data.isNull(row.getF().get(1).get("v")) ? "null" : row.getF().get(1).get("v").toString());
            ai.setAppInstanceId(Data.isNull(row.getF().get(2).get("v")) ? "null" : row.getF().get(2).get("v").toString());
            ai.setAppStore(Data.isNull(row.getF().get(3).get("v")) ? "null" : row.getF().get(3).get("v").toString());
            ai.setAppPlatform(Data.isNull(row.getF().get(4).get("v")) ? "null" : row.getF().get(4).get("v").toString());
            ai.setAppId(Data.isNull(row.getF().get(5).get("v")) ? "null" : row.getF().get(5).get("v").toString());

            Downloaded dl = new Downloaded(us, ai);
            dl.setUserId(row.getF().get(0).get("v").toString());
            dl.setVersion(ai.getAppVersion());
            dl.setCreateDate(Common.getBackDate(-1));
            list.add(dl);
        }
        return list;
    }

}
