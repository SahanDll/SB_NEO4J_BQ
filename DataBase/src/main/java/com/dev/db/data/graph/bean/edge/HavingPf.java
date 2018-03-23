package com.dev.db.data.graph.bean.edge;

import com.dev.db.data.graph.bean.node.Portfolio;
import com.dev.db.data.graph.bean.node.ScreenEngagement;
import com.dev.db.data.graph.bean.node.User;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.DateLong;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RelationshipEntity(type="HavingPf")
public class HavingPf {
    @GraphId
    @Id
    private Long relationshipId;
    @StartNode
    private User user;
    @EndNode
    private Portfolio portfolio;
    @Property(name="clntCode")
    private String clntCode;
    @Property(name="clntNric")
    private String clntNric;
    @Property(name="userId")
    private String userId;
    @Property(name="schema")
    private String schema;
    @Property(name="create_date")
    @DateLong
    private Date createDate;

    public HavingPf() {
    }

    public HavingPf(User user, Portfolio portfolio) {
        this.user = user;
        this.portfolio = portfolio;
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

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public String getClntCode() {
        return clntCode;
    }

    public void setClntCode(String clntCode) {
        this.clntCode = clntCode;
    }

    public String getClntNric() {
        return clntNric;
    }

    public void setClntNric(String clntNric) {
        this.clntNric = clntNric;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "HavingPf{" +
                "relationshipId=" + relationshipId +
                ", clntCode='" + clntCode + '\'' +
                ", clntNric='" + clntNric + '\'' +
                ", userId='" + userId + '\'' +
                ", schema='" + schema + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
