package com.dev.db.graph.adapter;

import com.dev.db.graph.bean.edge.Using;
import com.dev.db.graph.bean.node.DeviceInfo;
import com.dev.db.graph.bean.node.User;
import com.dev.util.Common;
import com.google.api.client.util.Data;
import com.google.api.services.bigquery.model.TableRow;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsingAdapter {
    private static final Logger LOGGER = Logger.getLogger(UsingAdapter.class);
    private static UsingAdapter self;

    private UsingAdapter(){
    }

    public static synchronized UsingAdapter getInstance() {
        if (self == null) {
            self = new UsingAdapter();
        }
        return self;
    }

    public List<Using> syncData(List<TableRow> rows) {
        List<Using> list = new ArrayList<>();
        if(null == rows)
            return list;
        for (TableRow row : rows) {
            if(Data.isNull(row.getF().get(0).get("v"))){
                continue;
            }

            User us = new User();
            us.setUserId(row.getF().get(0).get("v").toString());

            DeviceInfo di = new DeviceInfo();
            di.setDeviceCategory(Data.isNull(row.getF().get(1).get("v")) ? "null" : row.getF().get(1).get("v").toString());
            di.setMobileBrandName(Data.isNull(row.getF().get(2).get("v")) ? "null" : row.getF().get(2).get("v").toString());
            di.setMobileModelName(Data.isNull(row.getF().get(3).get("v")) ? "null" : row.getF().get(3).get("v").toString());
            di.setMobileMarketingName(Data.isNull(row.getF().get(4).get("v")) ? "null" : row.getF().get(4).get("v").toString());
            di.setDeviceModel(Data.isNull(row.getF().get(5).get("v")) ? "null" : row.getF().get(5).get("v").toString());
            di.setPlatformVersion(Data.isNull(row.getF().get(6).get("v")) ? "null" : row.getF().get(6).get("v").toString());
            di.setDeviceId(Data.isNull(row.getF().get(7).get("v")) ? "null" : row.getF().get(7).get("v").toString());
            di.setResettableDeviceId(Data.isNull(row.getF().get(8).get("v")) ? "null" : row.getF().get(8).get("v").toString());
            di.setUserDefaultLanguage(Data.isNull(row.getF().get(9).get("v")) ? "null" : row.getF().get(9).get("v").toString());
            di.setDeviceTimeZoneOffsetSeconds(Integer.parseInt(Data.isNull(row.getF().get(10).get("v")) ? "0" : row.getF().get(10).get("v").toString()));
            di.setLimitedAdTracking(Boolean.parseBoolean(Data.isNull(row.getF().get(11).get("v")) ? "false" : row.getF().get(11).get("v").toString()));

            Using ui = new Using(us, di);
            ui.setUserId(row.getF().get(0).get("v").toString());
            ui.setDevice(di.getDeviceModel());
            ui.setCreateDate(Common.getBackDate(-1));
            list.add(ui);
        }
        return list;
    }

}
