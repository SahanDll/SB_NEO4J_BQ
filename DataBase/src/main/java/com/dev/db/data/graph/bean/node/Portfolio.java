package com.dev.db.data.graph.bean.node;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(value = {"handler"})
@NodeEntity(label="Portfolio")
public class Portfolio {
    @GraphId
    @Id
    private Long id;
    @Property(name="clntCode")
    private String clntCode;
    @Property(name="clntNric")
    private String clntNric;
    @Property(name="userId")
    private String userId;
    @Property(name="schema")
    private String schema;
    @Property(name="portfolio")
    private List<Object> portfolio;

    @Relationship(type = "HavingPf", direction = Relationship.OUTGOING)
    private Set<User> user;

    public Portfolio() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Object> getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(List<Object> portfolio) {
        this.portfolio = portfolio;
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
