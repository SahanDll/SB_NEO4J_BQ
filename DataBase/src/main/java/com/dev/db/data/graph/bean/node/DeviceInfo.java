package com.dev.db.data.graph.bean.node;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.*;
import org.springframework.data.annotation.Id;

import java.util.Set;

@JsonIgnoreProperties(value = {"handler"})
@NodeEntity(label="DeviceInfo")
public class DeviceInfo {
    @GraphId
    @Id
    private Long id;
    @Property(name="deviceCategory")
    private String deviceCategory;
    @Property(name="mobileBrandName")
    private String mobileBrandName;
    @Property(name="mobileModelName")
    private String mobileModelName;
    @Property(name="mobileMarketingName")
    private String mobileMarketingName;
    @Property(name="deviceModel")
    private String deviceModel;
    @Property(name="platformVersion")
    private String platformVersion;
    @Property(name="deviceId")
    private String deviceId;
    @Property(name="resettableDeviceId")
    private String resettableDeviceId;
    @Property(name="userDefaultLanguage")
    private String userDefaultLanguage;
    @Property(name="deviceTimeZoneOffsetSeconds")
    private int deviceTimeZoneOffsetSeconds;
    @Property(name="limitedAdTracking")
    private boolean limitedAdTracking;

    @Relationship(type = "Using", direction = Relationship.OUTGOING)
    private Set<User> user;

    public DeviceInfo(){

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

    public String getDeviceCategory() {
        return deviceCategory;
    }

    public void setDeviceCategory(String deviceCategory) {
        this.deviceCategory = deviceCategory;
    }

    public String getMobileBrandName() {
        return mobileBrandName;
    }

    public void setMobileBrandName(String mobileBrandName) {
        this.mobileBrandName = mobileBrandName;
    }

    public String getMobileModelName() {
        return mobileModelName;
    }

    public void setMobileModelName(String mobileModelName) {
        this.mobileModelName = mobileModelName;
    }

    public String getMobileMarketingName() {
        return mobileMarketingName;
    }

    public void setMobileMarketingName(String mobileMarketingName) {
        this.mobileMarketingName = mobileMarketingName;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getResettableDeviceId() {
        return resettableDeviceId;
    }

    public void setResettableDeviceId(String resettableDeviceId) {
        this.resettableDeviceId = resettableDeviceId;
    }

    public String getUserDefaultLanguage() {
        return userDefaultLanguage;
    }

    public void setUserDefaultLanguage(String userDefaultLanguage) {
        this.userDefaultLanguage = userDefaultLanguage;
    }

    public int getDeviceTimeZoneOffsetSeconds() {
        return deviceTimeZoneOffsetSeconds;
    }

    public void setDeviceTimeZoneOffsetSeconds(int deviceTimeZoneOffsetSeconds) {
        this.deviceTimeZoneOffsetSeconds = deviceTimeZoneOffsetSeconds;
    }

    public boolean isLimitedAdTracking() {
        return limitedAdTracking;
    }

    public void setLimitedAdTracking(boolean limitedAdTracking) {
        this.limitedAdTracking = limitedAdTracking;
    }

    @Override
    public String toString() {
        return "DeviceInfo{" +
                "id=" + id +
                ", deviceCategory='" + deviceCategory + '\'' +
                ", mobileBrandName='" + mobileBrandName + '\'' +
                ", mobileModelName='" + mobileModelName + '\'' +
                ", mobileMarketingName='" + mobileMarketingName + '\'' +
                ", deviceModel='" + deviceModel + '\'' +
                ", platformVersion='" + platformVersion + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", resettableDeviceId='" + resettableDeviceId + '\'' +
                ", userDefaultLanguage='" + userDefaultLanguage + '\'' +
                ", deviceTimeZoneOffsetSeconds=" + deviceTimeZoneOffsetSeconds +
                ", limitedAdTracking=" + limitedAdTracking +
                '}';
    }
}
