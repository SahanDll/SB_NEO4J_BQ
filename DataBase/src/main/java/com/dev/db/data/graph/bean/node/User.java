package com.dev.db.data.graph.bean.node;

import com.dev.db.data.graph.bean.edge.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.DateLong;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@JsonIgnoreProperties(value = {"handler"})
@NodeEntity(label="User")
public class User {
    @GraphId
    @Id
    private Long id;
    @Property(name="user_id")
    private String userId;
    @Property(name="nric")
    private String nric;
    @Property(name="first-login")
    @DateLong
    private Date firstLogin;
    @Property(name="alerts")
    private List<Long> alerts;
    @Property(name="news-click")
    private List<Long> newsClick;
    @Property(name="price-click")
    private List<Long> priceClick;
    @Property(name="portfolio-click")
    private List<Long> portfolioClick;
    @Property(name="payment-click")
    private List<Long> paymentClick;
    @Property(name="entitlement-click")
    private List<Long> entitlementClick;
    @Property(name="insight-click")
    private List<Long> insightClick;
    @Property(name="system-click")
    private List<Long> systemClick;
    @Property(name="jill-click")
    private List<Long> jillClick;
    @Property(name="order-click")
    private List<Long> orderClick;

    @Relationship(type = "Login", direction = Relationship.OUTGOING)
    private Set<Login> login;
    @Relationship(type = "Using", direction = Relationship.OUTGOING)
    private Set<Using> using;
    @Relationship(type = "View", direction = Relationship.OUTGOING)
    private Set<View> view;
    @Relationship(type = "Downloaded", direction = Relationship.OUTGOING)
    private Set<Downloaded> downloaded;
    @Relationship(type = "Engage", direction = Relationship.OUTGOING)
    private Set<Engage> engage;
    @Relationship(type = "HavingWl", direction = Relationship.OUTGOING)
    private Set<HavingWl> havingWl;
    @Relationship(type = "HavingPf", direction = Relationship.OUTGOING)
    private Set<HavingPf> havingPf;

    public User() {
    }

    public Set<Login> getLogin() {
        return login;
    }

    public void setLogin(Set<Login> login) {
        this.login = login;
    }

    public Set<Using> getUsing() {
        return using;
    }

    public void setUsing(Set<Using> using) {
        this.using = using;
    }

    public Set<View> getView() {
        return view;
    }

    public void setView(Set<View> view) {
        this.view = view;
    }

    public Set<Downloaded> getDownloaded() {
        return downloaded;
    }

    public void setDownloaded(Set<Downloaded> downloaded) {
        this.downloaded = downloaded;
    }

    public Set<Engage> getEngage() {
        return engage;
    }

    public void setEngage(Set<Engage> engage) {
        this.engage = engage;
    }

    public Set<HavingWl> getHavingWl() {
        return havingWl;
    }

    public void setHavingWl(Set<HavingWl> havingWl) {
        this.havingWl = havingWl;
    }

    public Set<HavingPf> getHavingPf() {
        return havingPf;
    }

    public void setHavingPf(Set<HavingPf> havingPf) {
        this.havingPf = havingPf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNric() {
        return nric;
    }

    public void setNric(String nric) {
        this.nric = nric;
    }

    public Date getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Date firstLogin) {
        this.firstLogin = firstLogin;
    }

    public List<Long> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Long> alerts) {
        this.alerts = alerts;
    }

    public List<Long> getNewsClick() {
        return newsClick;
    }

    public void setNewsClick(List<Long> newsClick) {
        this.newsClick = newsClick;
    }

    public List<Long> getPriceClick() {
        return priceClick;
    }

    public void setPriceClick(List<Long> priceClick) {
        this.priceClick = priceClick;
    }

    public List<Long> getPortfolioClick() {
        return portfolioClick;
    }

    public void setPortfolioClick(List<Long> portfolioClick) {
        this.portfolioClick = portfolioClick;
    }

    public List<Long> getPaymentClick() {
        return paymentClick;
    }

    public void setPaymentClick(List<Long> paymentClick) {
        this.paymentClick = paymentClick;
    }

    public List<Long> getEntitlementClick() {
        return entitlementClick;
    }

    public void setEntitlementClick(List<Long> entitlementClick) {
        this.entitlementClick = entitlementClick;
    }

    public List<Long> getInsightClick() {
        return insightClick;
    }

    public void setInsightClick(List<Long> insightClick) {
        this.insightClick = insightClick;
    }

    public List<Long> getSystemClick() {
        return systemClick;
    }

    public void setSystemClick(List<Long> systemClick) {
        this.systemClick = systemClick;
    }

    public List<Long> getJillClick() {
        return jillClick;
    }

    public void setJillClick(List<Long> jillClick) {
        this.jillClick = jillClick;
    }

    public List<Long> getOrderClick() {
        return orderClick;
    }

    public void setOrderClick(List<Long> orderClick) {
        this.orderClick = orderClick;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", nric='" + nric + '\'' +
                '}';
    }
}
