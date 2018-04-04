package com.dev.db.data.graph.adapter;

import com.dev.db.data.graph.bean.edge.Login;
import com.dev.db.data.graph.bean.node.GeoInfo;
import com.dev.db.data.graph.bean.node.User;
import com.dev.db.util.Common;
import com.google.api.client.util.Data;
import com.google.api.services.bigquery.model.TableRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class LoginAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginAdapter.class);
    private static LoginAdapter self;

    private LoginAdapter(){
    }

    public static synchronized LoginAdapter getInstance() {
        if (self == null) {
            self = new LoginAdapter();
        }
        return self;
    }

    public List<Login> syncData(List<TableRow> rows) {
        List<Login> list = new ArrayList<>();
        if(null == rows)
            return list;
        for (TableRow row : rows) {

            if(Data.isNull(row.getF().get(0).get("v"))){
                continue;
            }

            User us = new User();
            us.setUserId(row.getF().get(0).get("v").toString());

            GeoInfo gi = new GeoInfo();
            gi.setContinent(Data.isNull(row.getF().get(1).get("v")) ? "null" : row.getF().get(1).get("v").toString());
            gi.setCountry(Data.isNull(row.getF().get(2).get("v")) ? "null" : row.getF().get(2).get("v").toString());
            gi.setRegion(Data.isNull(row.getF().get(3).get("v")) ? "null" : row.getF().get(3).get("v").toString());
            gi.setCity(Data.isNull(row.getF().get(4).get("v")) ? "null" : row.getF().get(4).get("v").toString());

            Login lif = new Login(us, gi);
            lif.setUserId(row.getF().get(0).get("v").toString());
            lif.setLocation(gi.getCity());
            lif.getLoginDate().add(Common.getStartOfDay(Common.getBackDate(-1)).getTime());
            lif.setCreateDate(Common.getCurrentDate());
            list.add(lif);
        }
        return list;
    }

}
