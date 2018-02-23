package com.dev.db.graph.service.inte;

import com.dev.db.graph.bean.edge.Using;
import org.json.simple.JSONObject;

import java.util.Map;

/**
 * Created by N5608296 on 14/09/17.
 */
public interface UsingService {
    public Using create(Using using);

    public Map<String, Object> read(String usingId);

    public Map<String, Object> update(Using using);

    public Map<String, Object> delete(String usingId);

    public JSONObject readAll();

}
