package com.dev.db.data.sql.fmt.bean.alert;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by N5608296 on 18/08/17.
 */
@SuppressWarnings("ALL")
@Entity
@Table(name = "AlertFireLog", schema = "ALERT")
@NamedQueries({
        @NamedQuery(query = "SELECT a FROM AlertFireLog a WHERE a.logId = :logId ", name = "get alert fire log"),
        @NamedQuery(query = "SELECT a FROM AlertFireLog a WHERE a.alertType = :alertType and a.triggerTime > :triggerTime ORDER BY a.logId", name = "get alert fire log type")
})
public class AlertFireLog {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "LogId")
    private Long logId;
    @Column(name = "AlertType")
    private String alertType;
    @Column(name = "AlertSource")
    private String alertSource;
    @Column(name = "StkCode")
    private String stkCode;
    @Column(name = "Title")
    private String title;
    @Column(name = "ClntNric")
    private String clntNric;
    @Column(name = "Remarks")
    private String remarks;
    @Column(name = "StkName")
    private String stkName;
    @Column(name = "ShortContent")
    private String shortContent;
    @Column(name = "FullContent")
    private String fullContent;
    @Column(name = "AlertDeleted")
    private Integer alertDeleted;
    @Column(name = "AlertRead")
    private Integer alertRead;
    @Column(name = "Entity")
    private Integer entity;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TriggerTime")
    private Date triggerTime;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public String getAlertSource() {
        return alertSource;
    }

    public void setAlertSource(String alertSource) {
        this.alertSource = alertSource;
    }

    public String getStkCode() {
        return stkCode;
    }

    public void setStkCode(String stkCode) {
        this.stkCode = stkCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClntNric() {
        return clntNric;
    }

    public void setClntNric(String clntNric) {
        this.clntNric = clntNric;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStkName() {
        return stkName;
    }

    public void setStkName(String stkName) {
        this.stkName = stkName;
    }

    public String getShortContent() {
        return shortContent;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

    public String getFullContent() {
        return fullContent;
    }

    public void setFullContent(String fullContent) {
        this.fullContent = fullContent;
    }

    public Integer getAlertDeleted() {
        return alertDeleted;
    }

    public void setAlertDeleted(Integer alertDeleted) {
        this.alertDeleted = alertDeleted;
    }

    public Integer getAlertRead() {
        return alertRead;
    }

    public void setAlertRead(Integer alertRead) {
        this.alertRead = alertRead;
    }

    public Integer getEntity() {
        return entity;
    }

    public void setEntity(Integer entity) {
        this.entity = entity;
    }

    public Date getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(Date triggerTime) {
        this.triggerTime = triggerTime;
    }

    @Override
    public String toString() {
        return "AlertFireLog{" +
                "logId=" + logId +
                ", alertType='" + alertType + '\'' +
                ", alertSource='" + alertSource + '\'' +
                ", stkCode='" + stkCode + '\'' +
                ", title='" + title + '\'' +
                ", clntNric='" + clntNric + '\'' +
                ", remarks='" + remarks + '\'' +
                ", stkName='" + stkName + '\'' +
                ", shortContent='" + shortContent + '\'' +
                ", fullContent='" + fullContent + '\'' +
                ", alertDeleted=" + alertDeleted +
                ", alertRead=" + alertRead +
                ", entity=" + entity +
                ", triggerTime=" + triggerTime +
                '}';
    }
}
