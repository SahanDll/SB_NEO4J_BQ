package com.dev.db.data.graph.service.impl;

import com.dev.db.data.graph.bean.edge.Open;
import com.dev.db.data.graph.bean.node.Notification;
import com.dev.db.data.graph.bean.node.User;
import com.dev.db.data.graph.repository.edge.OpenRepository;
import com.dev.db.data.graph.repository.node.NotificationRepository;
import com.dev.db.data.graph.repository.node.UserRepository;
import com.dev.db.data.graph.service.inte.OpenService;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;


/**
 * Created by N5608296 on 14/09/17.
 */
@Service
public class OpenServiceImpl implements OpenService {
    private static final Logger logger = LoggerFactory.getLogger(OpenServiceImpl.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    OpenRepository openRepository;

    @Override
    public Open create(Open open) {
        Open op = openRepository.findTop1ByUserIdAndNotificationTypeAndOpenTime(open.getUserId(), open.getNotificationType(), open.getOpenTime());
        User us = userRepository.findTop1ByUserId(open.getUserId());
        if(null == us){
            open.getUser().setFirstLogin(new Date());
        }
        Notification nt = notificationRepository.findTop1ByType(open.getNotificationType());

        if(null == op){
            if(null != us){
                open.getUser().setId(us.getId());
            }
            if(null != nt){
                open.getNotification().setId(nt.getId());
            }
            open.setUser(userRepository.save(open.getUser()));
            open.setNotification(notificationRepository.save(open.getNotification()));
            return openRepository.save(open);
        }else{
            open.getUser().setId(op.getUser().getId());
            op.setUser(open.getUser());
            open.getNotification().setId(op.getNotification().getId());
            op.setNotification(open.getNotification());
            return openRepository.save(op);
        }
    }

    @Override
    public Map<String, Object> read(String openId) {
        return null;
    }

    @Override
    public Map<String, Object> update(Open open) {
        return null;
    }

    @Override
    public Map<String, Object> delete(String openId) {
        return null;
    }

    @Override
    public JSONObject readAll() {
        return null;
    }
}
