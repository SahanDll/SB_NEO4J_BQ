package com.dev.db.data.graph.bean.edge;

import com.dev.db.data.graph.bean.node.ScreenEngagement;
import com.dev.db.data.graph.bean.node.User;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.DateLong;
import org.springframework.data.annotation.Id;

import java.util.*;

@RelationshipEntity(type="Engage")
public class Engage {
    @GraphId
    @Id
    private Long relationshipId;
    @StartNode
    private User user;
    @EndNode
    private ScreenEngagement screen;
    @Property(name="user_id")
    private String userId;
    @Property(name="screen_name")
    private String screenName;
    @Property(name="view_time")
    private List<Long> viewTime;
    @Property(name="view_length")
    private List<Long> viewLength;
    @Property(name="platform")
    private String platform;
    @Property(name="create_date")
    @DateLong
    private Date createDate;

    public Engage() {
    }

    public Engage(User user, ScreenEngagement screen) {
        this.user = user;
        this.screen = screen;
        this.viewTime = new ArrayList<>();
        this.viewLength = new ArrayList<>();
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

    public ScreenEngagement getScreen() {
        return screen;
    }

    public void setScreen(ScreenEngagement screen) {
        this.screen = screen;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public List<Long> getViewTime() {
        return viewTime;
    }

    public void setViewTime(List<Long> viewTime) {
        this.viewTime = viewTime;
    }

    public List<Long> getViewLength() {
        return viewLength;
    }

    public void setViewLength(List<Long> viewLength) {
        this.viewLength = viewLength;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Engage{" +
                "relationshipId=" + relationshipId +
                ", user=" + user +
                ", screen=" + screen +
                ", userId='" + userId + '\'' +
                ", screenName='" + screenName + '\'' +
                ", viewTime=" + viewTime +
                ", viewLength=" + viewLength +
                ", platform='" + platform + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
