package com.dev.db.data.graph.bean.edge;

import com.dev.db.data.graph.bean.node.ScreenTransition;
import com.dev.db.data.graph.bean.node.User;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.DateLong;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RelationshipEntity(type="View")
public class View {
    @GraphId
    @Id
    private Long relationshipId;
    @StartNode
    private User user;
    @EndNode
    private ScreenTransition screen;
    @Property(name="user_id")
    private String userId;
    @Property(name="from_screen_name")
    private String fromScreenName;
    @Property(name="to_screen_name")
    private String toScreenName;
    @Property(name="view_time")
    private List<Long> viewTime;
    @Property(name="platform")
    private String platform;
    @Property(name="create_date")
    @DateLong
    private Date createDate;

    public View() {
    }

    public View(User user, ScreenTransition screen) {
        this.user = user;
        this.screen = screen;
        this.viewTime = new ArrayList<>();
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

    public ScreenTransition getScreen() {
        return screen;
    }

    public void setScreen(ScreenTransition screen) {
        this.screen = screen;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getFromScreenName() {
        return fromScreenName;
    }

    public void setFromScreenName(String fromScreenName) {
        this.fromScreenName = fromScreenName;
    }

    public String getToScreenName() {
        return toScreenName;
    }

    public void setToScreenName(String toScreenName) {
        this.toScreenName = toScreenName;
    }


    public List<Long> getViewTime() {
        return viewTime;
    }

    public void setViewTime(List<Long> viewTime) {
        this.viewTime = viewTime;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "View{" +
                "relationshipId=" + relationshipId +
                ", user=" + user +
                ", screen=" + screen +
                ", userId='" + userId + '\'' +
                ", fromScreenName='" + fromScreenName + '\'' +
                ", toScreenName='" + toScreenName + '\'' +
                ", viewTime=" + viewTime +
                ", platform='" + platform + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
