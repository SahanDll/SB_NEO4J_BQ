package com.dev.db.graph.bean.edge;

import com.dev.db.graph.bean.node.GeoInfo;
import com.dev.db.graph.bean.node.User;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.DateLong;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RelationshipEntity(type="Login")
public class Login {
    @GraphId
    @Id
    private Long relationshipId;
    @StartNode
    private User user;
    @EndNode
    private GeoInfo geoInfo;

    @Property(name="user_id")
    private String userId;
    @Property(name="location")
    private String location;
    @Property(name="platform")
    private String platform;
    @Property(name="login_time")
    private List<Long> loginDate;
    @Property(name="create_date")
    @DateLong
    private Date createDate;

    public Login(){

    }

    public Login(User user, GeoInfo geoInfo){
        this.user = user;
        this.geoInfo = geoInfo;
        this.loginDate = new ArrayList<>();
    }

    public Long getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(Long relationshipId) {
        this.relationshipId = relationshipId;
    }

    public GeoInfo getGeoInfo() {
        return geoInfo;
    }

    public void setGeoInfo(GeoInfo geoInfo) {
        this.geoInfo = geoInfo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Long> getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(List<Long> loginDate) {
        this.loginDate = loginDate;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "Login{" +
                "relationshipId=" + relationshipId +
                ", user=" + user +
                ", geoInfo=" + geoInfo +
                ", userId='" + userId + '\'' +
                ", location='" + location + '\'' +
                ", platform='" + platform + '\'' +
                ", loginDate=" + loginDate +
                ", createDate=" + createDate +
                '}';
    }
}
