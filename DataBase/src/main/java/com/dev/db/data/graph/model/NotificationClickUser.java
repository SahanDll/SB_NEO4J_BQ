package com.dev.db.data.graph.model;

import lombok.Data;

@Data
public class NotificationClickUser {
    private String userId;
    private String type;
    private Long time;
}
