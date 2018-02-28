package com.dev.db.graph.service.inte;

import com.dev.db.graph.bean.node.User;
import org.json.simple.JSONObject;

import java.util.Map;

/**
 * Created by N5608296 on 14/09/17.
 */
public interface UserService {
    public User create(User user);

    public JSONObject read(String userId);

    public Map<String, Object> update(User user);

    public JSONObject delete(String userId);

    public JSONObject readAll();

}
