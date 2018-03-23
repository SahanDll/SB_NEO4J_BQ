package com.dev.db.data.graph.bean.edge;

import com.dev.db.data.graph.bean.node.Portfolio;
import com.dev.db.data.graph.bean.node.User;
import com.dev.db.data.graph.bean.node.Watchlist;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.DateLong;
import org.springframework.data.annotation.Id;

import java.util.Date;

@RelationshipEntity(type="HavingWl")
public class HavingWl {
    @GraphId
    @Id
    private Long relationshipId;
    @StartNode
    private User user;
    @EndNode
    private Watchlist watchlist;
    @Property(name="clntNric")
    private String clntNric;
    @Property(name="userId")
    private String userId;
    @Property(name="schema")
    private String schema;
    @Property(name="create_date")
    @DateLong
    private Date createDate;

    public HavingWl() {
    }

    public HavingWl(User user, Watchlist watchlist) {
        this.user = user;
        this.watchlist = watchlist;
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

    public Watchlist getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(Watchlist watchlist) {
        this.watchlist = watchlist;
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
                ", clntNric='" + clntNric + '\'' +
                ", userId='" + userId + '\'' +
                ", schema='" + schema + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
