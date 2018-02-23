package com.dev.db.graph.bean.node;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.annotation.Id;

import java.util.Set;

@JsonIgnoreProperties(value = {"handler"})
@NodeEntity(label="AppInfo")
public class AppInfo {
    @GraphId
    @Id
    private Long id;
    @Property(name="appVersion")
    private String appVersion;
    @Property(name="appInstanceId")
    private String appInstanceId;
    @Property(name="appStore")
    private String appStore;
    @Property(name="appPlatform")
    private String appPlatform;
    @Property(name="appId")
    private String appId;

    @Relationship(type = "Downloaded", direction = Relationship.OUTGOING)
    private Set<User> user;

    public AppInfo() {
    }


    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppInstanceId() {
        return appInstanceId;
    }

    public void setAppInstanceId(String appInstanceId) {
        this.appInstanceId = appInstanceId;
    }

    public String getAppStore() {
        return appStore;
    }

    public void setAppStore(String appStore) {
        this.appStore = appStore;
    }

    public String getAppPlatform() {
        return appPlatform;
    }

    public void setAppPlatform(String appPlatform) {
        this.appPlatform = appPlatform;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "id=" + id +
                ", appVersion='" + appVersion + '\'' +
                ", appInstanceId='" + appInstanceId + '\'' +
                ", appStore='" + appStore + '\'' +
                ", appPlatform='" + appPlatform + '\'' +
                ", appId='" + appId + '\'' +
                '}';
    }
}
