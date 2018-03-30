package com.dev.db.data.graph.service.inte;

import com.dev.db.data.graph.bean.node.User;
import com.dev.db.data.graph.model.NotificationClickUser;
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

    public User updateNotificationClick(NotificationClickUser user);

}
