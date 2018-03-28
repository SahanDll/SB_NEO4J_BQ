package com.dev.db.data.graph.service.inte;

import com.dev.db.data.graph.bean.edge.Trade;
import com.dev.db.data.graph.bean.edge.View;
import org.json.simple.JSONObject;

import java.util.Map;

/**
 * Created by N5608296 on 14/09/17.
 */
public interface TradeService {
    public Trade create(Trade trade);

    public Map<String, Object> read(String tradeId);

    public Map<String, Object> update(Trade trade);

    public Map<String, Object> delete(String tradeId);

    public JSONObject readAll();

}
