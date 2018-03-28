package com.dev.db.data.graph.bean.node;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(value = {"handler"})
@NodeEntity(label="Stock")
public class Stock {
    @GraphId
    @Id
    private Long id;
    @Property(name="stkCode")
    private String stkCode;
    @Property(name="stkName")
    private String stkName;

    @Relationship(type = "Trade", direction = Relationship.OUTGOING)
    private Set<User> user;

    public Stock() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStkCode() {
        return stkCode;
    }

    public void setStkCode(String stkCode) {
        this.stkCode = stkCode;
    }

    public String getStkName() {
        return stkName;
    }

    public void setStkName(String stkName) {
        this.stkName = stkName;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }
}
