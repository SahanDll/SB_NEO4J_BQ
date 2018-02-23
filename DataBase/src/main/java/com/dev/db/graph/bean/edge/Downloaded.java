package com.dev.db.graph.bean.edge;

import com.dev.db.graph.bean.node.AppInfo;
import com.dev.db.graph.bean.node.User;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.DateLong;
import org.springframework.data.annotation.Id;

import java.util.Date;

@RelationshipEntity(type="Downloaded")
public class Downloaded {
    @GraphId
    @Id
    private Long relationshipId;
    @StartNode
    private User user;
    @EndNode
    private AppInfo appInfo;
    @Property(name="user_id")
    private String userId;
    @Property(name="version")
    private String version;
    @Property(name="platform")
    private String platform;
    @Property(name="create_date")
    @DateLong
    private Date createDate;

    public Downloaded() {
    }

    public Downloaded(User user, AppInfo appInfo) {
        this.user = user;
        this.appInfo = appInfo;
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

    public AppInfo getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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
        return "Downloaded{" +
                "relationshipId=" + relationshipId +
                ", user=" + user +
                ", appInfo=" + appInfo +
                ", userId='" + userId + '\'' +
                ", version='" + version + '\'' +
                ", platform='" + platform + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
