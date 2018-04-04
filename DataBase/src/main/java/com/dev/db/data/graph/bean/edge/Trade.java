package com.dev.db.data.graph.bean.edge;

import com.dev.db.data.graph.bean.node.ScreenTransition;
import com.dev.db.data.graph.bean.node.Stock;
import com.dev.db.data.graph.bean.node.User;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.DateLong;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RelationshipEntity(type="Trade")
public class Trade {
    @GraphId
    @Id
    private Long relationshipId;
    @StartNode
    private User user;
    @EndNode
    private Stock stock;
    @Property(name="user_id")
    private String userId;
    @Property(name="trade_time")
    @DateLong
    private Date tradeTime;
    @Property(name="screen")
    private String screen;
    @Property(name="order_type")
    private String orderType;
    @Property(name="stkCode")
    private String stkCode;
    @Property(name="quantity")
    private Integer quantity;
    @Property(name="price")
    private Double price;
    @Property(name="volume")
    private Double volume;
    @Property(name="create_date")
    @DateLong
    private Date createDate;

    public Trade() {
    }

    public Trade(User user, Stock stock) {
        this.user = user;
        this.stock = stock;
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

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getStkCode() {
        return stkCode;
    }

    public void setStkCode(String stkCode) {
        this.stkCode = stkCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "relationshipId=" + relationshipId +
                ", userId='" + userId + '\'' +
                ", tradeTime=" + tradeTime +
                ", orderType='" + orderType + '\'' +
                ", stkCode='" + stkCode + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", volume=" + volume +
                ", createDate=" + createDate +
                ", screen=" + screen +
                '}';
    }
}
