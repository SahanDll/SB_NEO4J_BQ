package com.dev.db.data.sql.fmt.bean.alert;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@SuppressWarnings("ALL")
@Entity
@Table(name="ClientSettingMst", schema="ALERT")
@Data
public class SettingMst {
    @Id
    @Column(name = "IndexId")
    private Long indexId;
    @Column(name = "ClntNric")
    private String clntNric;
    @Column(name = "PriceEnabled")
    private Integer priceEnabled;
    @Column(name = "PortfolioEnabled")
    private Integer portfolioEnabled;
    @Column(name = "NewsEnabled")
    private Integer newsEnabled;
    @Column(name = "CaAnnouncementEnabled")
    private Integer caAnnouncementEnabled;
    @Column(name = "MkeInsightsEnabled")
    private Integer mkeInsightsEnabled;
    @Column(name = "PaymentEnabled")
    private Integer paymentEnabled;
    @Column(name = "MarketingEnabled")
    private Integer marketingEnabled;
    @Column(name = "SystemEnabled")
    private Integer systemEnabled;
    @Column(name = "GcmToken")
    private String gcmToken;
    @Column(name = "MobileUuid")
    private String mobileUuid;
    @Column(name = "MobileOs")
    private String mobileOs;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LastModified")
    private Date lastModified;

    public Long getIndexId() {
        return indexId;
    }

    public void setIndexId(Long indexId) {
        this.indexId = indexId;
    }

    public String getClntNric() {
        return clntNric;
    }

    public void setClntNric(String clntNric) {
        this.clntNric = clntNric;
    }

    public Integer getPriceEnabled() {
        return priceEnabled;
    }

    public void setPriceEnabled(Integer priceEnabled) {
        this.priceEnabled = priceEnabled;
    }

    public Integer getPortfolioEnabled() {
        return portfolioEnabled;
    }

    public void setPortfolioEnabled(Integer portfolioEnabled) {
        this.portfolioEnabled = portfolioEnabled;
    }

    public Integer getNewsEnabled() {
        return newsEnabled;
    }

    public void setNewsEnabled(Integer newsEnabled) {
        this.newsEnabled = newsEnabled;
    }

    public Integer getCaAnnouncementEnabled() {
        return caAnnouncementEnabled;
    }

    public void setCaAnnouncementEnabled(Integer caAnnouncementEnabled) {
        this.caAnnouncementEnabled = caAnnouncementEnabled;
    }

    public Integer getMkeInsightsEnabled() {
        return mkeInsightsEnabled;
    }

    public void setMkeInsightsEnabled(Integer mkeInsightsEnabled) {
        this.mkeInsightsEnabled = mkeInsightsEnabled;
    }

    public Integer getPaymentEnabled() {
        return paymentEnabled;
    }

    public void setPaymentEnabled(Integer paymentEnabled) {
        this.paymentEnabled = paymentEnabled;
    }

    public Integer getMarketingEnabled() {
        return marketingEnabled;
    }

    public void setMarketingEnabled(Integer marketingEnabled) {
        this.marketingEnabled = marketingEnabled;
    }

    public Integer getSystemEnabled() {
        return systemEnabled;
    }

    public void setSystemEnabled(Integer systemEnabled) {
        this.systemEnabled = systemEnabled;
    }

    public String getGcmToken() {
        return gcmToken;
    }

    public void setGcmToken(String gcmToken) {
        this.gcmToken = gcmToken;
    }

    public String getMobileUuid() {
        return mobileUuid;
    }

    public void setMobileUuid(String mobileUuid) {
        this.mobileUuid = mobileUuid;
    }

    public String getMobileOs() {
        return mobileOs;
    }

    public void setMobileOs(String mobileOs) {
        this.mobileOs = mobileOs;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String toString() {
        return "ClientSettingMaster{" +
                "indexId=" + indexId +
                ", clntNric='" + clntNric + '\'' +
                ", priceEnabled=" + priceEnabled +
                ", portfolioEnabled=" + portfolioEnabled +
                ", newsEnabled=" + newsEnabled +
                ", caAnnouncementEnabled=" + caAnnouncementEnabled +
                ", mkeInsightsEnabled=" + mkeInsightsEnabled +
                ", paymentEnabled=" + paymentEnabled +
                ", marketingEnabled=" + marketingEnabled +
                ", systemEnabled=" + systemEnabled +
                ", gcmToken='" + gcmToken + '\'' +
                ", mobileUuid='" + mobileUuid + '\'' +
                ", mobileOs='" + mobileOs + '\'' +
                ", lastModified=" + lastModified +
                '}';
    }
}
