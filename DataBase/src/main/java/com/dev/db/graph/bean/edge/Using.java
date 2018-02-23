package com.dev.db.graph.bean.edge;

import com.dev.db.graph.bean.node.DeviceInfo;
import com.dev.db.graph.bean.node.User;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.DateLong;
import org.springframework.data.annotation.Id;

import java.util.Date;

@RelationshipEntity(type="Using")
public class Using {
    @GraphId
    @Id
    private Long relationshipId;
    @StartNode
    private User user;
    @EndNode
    private DeviceInfo deviceInfo;
    @Property(name="user_id")
    private String userId;
    @Property(name="device")
    private String device;
    @Property(name="platform")
    private String platform;
    @Property(name="create_date")
    @DateLong
    private Date createDate;

    public Using() {
    }

    public Using(User user, DeviceInfo deviceInfo) {
        this.user = user;
        this.deviceInfo = deviceInfo;
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

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
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
        return "Using{" +
                "relationshipId=" + relationshipId +
                ", user=" + user +
                ", deviceInfo=" + deviceInfo +
                ", userId='" + userId + '\'' +
                ", device='" + device + '\'' +
                ", platform='" + platform + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
