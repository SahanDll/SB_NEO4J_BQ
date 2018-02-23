package com.dev.db.graph.service.inte;

import com.dev.db.graph.bean.edge.Login;
import org.json.simple.JSONObject;

import java.util.Map;

/**
 * Created by N5608296 on 14/09/17.
 */
public interface LoginService {
    public Login create(Login login);

    public Map<String, Object> read(String loginId);

    public Map<String, Object> update(Login login);

    public Map<String, Object> delete(String loginId);

    public JSONObject readAll();

}
