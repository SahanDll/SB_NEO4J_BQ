package com.dev.db.data.graph.service.inte;

import com.dev.db.data.graph.bean.edge.View;
import org.json.simple.JSONObject;

import java.util.Map;

/**
 * Created by N5608296 on 14/09/17.
 */
public interface ViewService {
    public View create(View view);

    public Map<String, Object> read(String viewId);

    public Map<String, Object> update(View view);

    public Map<String, Object> delete(String viewId);

    public JSONObject readAll();

}
