package com.dev.db.data.graph.service.inte;

import com.dev.db.data.graph.bean.edge.Engage;
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
