package com.dev.db.data.graph.adapter;

import com.dev.db.data.graph.bean.edge.Trade;
import com.dev.db.data.graph.bean.edge.View;
import com.dev.db.data.graph.bean.node.ScreenTransition;
import com.dev.db.data.graph.bean.node.Stock;
import com.dev.db.data.graph.bean.node.User;
import com.dev.db.util.Common;
import com.google.api.client.util.Data;
import com.google.api.services.bigquery.model.TableRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.st;

@Component
public class TradeAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(TradeAdapter.class);
    private static TradeAdapter self;

    private TradeAdapter(){
    }

    public static synchronized TradeAdapter getInstance() {
        if (self == null) {
            self = new TradeAdapter();
        }
        return self;
    }

    public List<Trade> syncData(List<TableRow> rows) {
        List<Trade> list = new ArrayList<>();
        if(null == rows)
            return list;
        User us = null;
        Stock stk = null;
        Trade trd = null;
        long recordTime = 0;
        boolean newRecord = false;
        for (TableRow row : rows) {
            try {
                if(Data.isNull(row.getF().get(0).get("v")) || Data.isNull(row.getF().get(7).get("v"))){
                    continue;
                }

                if (recordTime != Long.parseLong(row.getF().get(7).get("v").toString())){
                    newRecord = true;
                    recordTime = Long.parseLong(row.getF().get(7).get("v").toString());
                    if(null != trd){
                        trd.setVolume(trd.getQuantity()*trd.getPrice());
                        list.add(trd);
                    }
                    trd = null;
                }else{
                    newRecord = false;
                }

                if (newRecord){
                    stk = new Stock();
                    us = new User();
                    us.setUserId(row.getF().get(0).get("v").toString());
                    trd = new Trade(us, stk);
                    trd.setUserId(us.getUserId());
                    trd.setTradeTime(recordTime);
                    trd.setCreateDate(new Date());
                }
                if(!Data.isNull(row.getF().get(2).get("v"))) {
                    switch (row.getF().get(2).get("v").toString()) {
                        case "firebase_screen":
                            trd.setScreen(row.getF().get(3).get("v").toString());
                            break;
                        case "Stock_Code":
                            trd.setStkCode(row.getF().get(3).get("v").toString());
                            stk.setStkCode(row.getF().get(3).get("v").toString());
                            break;
                        case "Quantity":
                            trd.setQuantity(Integer.valueOf(row.getF().get(4).get("v").toString()));
                            break;
                        case "Price":
                            trd.setPrice(Double.valueOf(row.getF().get(6).get("v").toString())/1000);
                            break;
                        case "Order_Type":
                            trd.setOrderType(row.getF().get(3).get("v").toString());
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
