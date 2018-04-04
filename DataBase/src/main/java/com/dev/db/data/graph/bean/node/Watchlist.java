package com.dev.db.data.graph.bean.node;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.*;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(value = {"handler"})
@NodeEntity(label="Watchlist")
public class Watchlist {
    @GraphId
    @Id
    private Long id;
    @Property(name="clntNric")
    private String clntNric;
    @Property(name="userId")
    private String userId;
    @Property(name="schema")
    private String schema;
    @Property(name="watchlist")
    private List<Object> watchlist;

    @Relationship(type = "HavingWl", direction = Relationship.OUTGOING)
    private Set<User> user;

    public Watchlist() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Object> getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(List<Object> watchlist) {
        this.watchlist = watchlist;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }
}
