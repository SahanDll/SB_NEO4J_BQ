package com.dev.db.data.graph.bean.edge;

import com.dev.db.data.graph.bean.node.Notification;
import com.dev.db.data.graph.bean.node.Stock;
import com.dev.db.data.graph.bean.node.User;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.DateLong;
import org.springframework.data.annotation.Id;

import java.util.Date;

@RelationshipEntity(type="Open")
public class Open {
    @GraphId
    @Id
    private Long relationshipId;
    @StartNode
    private User user;
    @EndNode
    private Notification notification;
    @Property(name="user_id")
    private String userId;
    @Property(name="alert_id")
    private String alertId;
    @Property(name="open_time")
    @DateLong
    private Date openTime;
    @Property(name="screen")
    private String screen;
    @Property(name="notification_type")
    private String notificationType;
    @Property(name="create_date")
    @DateLong
    private Date createDate;

    public Open() {
    }

    public Open(User user, Notification notification) {
        this.user = user;
        this.notification = notification;
    }

    public Long getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(Long relationshipId) {
        this.relationshipId = relationshipId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAlertId() {
        return alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Open{" +
                "relationshipId=" + relationshipId +
                ", userId='" + userId + '\'' +
                ", alertId='" + alertId + '\'' +
                ", openTime=" + openTime +
                ", screen='" + screen + '\'' +
                ", notificationType='" + notificationType + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
