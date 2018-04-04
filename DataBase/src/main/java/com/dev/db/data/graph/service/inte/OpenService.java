package com.dev.db.data.graph.service.inte;

import com.dev.db.data.graph.bean.edge.Open;
import com.dev.db.data.graph.bean.edge.Trade;
import com.dev.db.data.graph.bean.node.Notification;
import org.json.simple.JSONObject;

import java.util.Map;

/**
 * Created by N5608296 on 14/09/17.
 */
public interface OpenService {
    public Open create(Open open);

    public Map<String, Object> read(String openId);

    public Map<String, Object> update(Open open);

    public Map<String, Object> delete(String openId);

    public JSONObject readAll();

}
