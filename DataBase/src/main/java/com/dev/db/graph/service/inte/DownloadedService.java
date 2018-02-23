package com.dev.db.graph.service.inte;

import com.dev.db.graph.bean.edge.Downloaded;
import org.json.simple.JSONObject;

import java.util.Map;

/**
 * Created by N5608296 on 14/09/17.
 */
public interface DownloadedService {
    public Downloaded create(Downloaded downloaded);

    public Map<String, Object> read(String downloadedId);

    public Map<String, Object> update(Downloaded downloaded);

    public Map<String, Object> delete(String downloadedId);

    public JSONObject readAll();

}
