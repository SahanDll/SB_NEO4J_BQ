package com.dev.db.data.graph.adapter;

import com.dev.db.data.graph.bean.edge.View;
import com.dev.db.data.graph.bean.node.ScreenTransition;
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
public class ViewAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ViewAdapter.class);
    private static ViewAdapter self;

    private ViewAdapter(){
    }

    public static synchronized ViewAdapter getInstance() {
        if (self == null) {
            self = new ViewAdapter();
        }
        return self;
    }

    public List<View> syncData(List<TableRow> rows) {
        List<View> list = new ArrayList<>();
        if(null == rows)
            return list;
        User us = null;
        ScreenTransition st = null;
        for (TableRow row : rows) {
            if(Data.isNull(row.getF().get(0).get("v")) || Data.isNull(row.getF().get(4).get("v"))){
                continue;
            }
            if(!Data.isNull(row.getF().get(3).get("v"))){
                if("firebase_previous_screen".equalsIgnoreCase(row.getF().get(2).get("v").toString())){
                    st = new ScreenTransition();
                    st.setPreviousScreen(Data.isNull(row.getF().get(3).get("v")) ? "null" : row.getF().get(3).get("v").toString());
                }
                if("firebase_screen".equalsIgnoreCase(row.getF().get(2).get("v").toString())){
                    if (null != st) {
                        us = new User();
                        us.setUserId(row.getF().get(0).get("v").toString());

                        st.setScreen(Data.isNull(row.getF().get(3).get("v")) ? "null" : row.getF().get(3).get("v").toString());

                        View vi = new View(us, st);
                        vi.setUserId(row.getF().get(0).get("v").toString());
                        vi.getViewTime().add(Long.parseLong(row.getF().get(4).get("v").toString()));
                        vi.setFromScreenName(st.getPreviousScreen());
                        vi.setToScreenName(st.getScreen());
                        vi.setCreateDate(Common.getBackDate(-1));
                        list.add(vi);
                        st = null;
                    }
                }
            }
        }
        return list;
    }

}
