package com.dev.db.graph.service.inte;

import com.dev.db.graph.bean.edge.Engage;
import com.dev.db.graph.bean.edge.View;
import org.json.simple.JSONObject;

import java.util.Map;

/**
 * Created by N5608296 on 14/09/17.
 */
public interface EngageService {
    public Engage create(Engage engage);

    public Map<String, Object> read(String engageId);

    public Map<String, Object> update(Engage engage);

    public Map<String, Object> delete(String engageId);

    public JSONObject readAll();

}
