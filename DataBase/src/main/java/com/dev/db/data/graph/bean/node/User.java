package com.dev.db.data.graph.bean.node;

import com.dev.db.data.graph.bean.edge.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.annotation.Id;

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

    @Relationship(type = "Login", direction = Relationship.INCOMING)
    private Set<Login> login;
    @Relationship(type = "Using", direction = Relationship.INCOMING)
    private Set<Using> using;
    @Relationship(type = "View", direction = Relationship.INCOMING)
    private Set<View> view;
    @Relationship(type = "Downloaded", direction = Relationship.INCOMING)
    private Set<Downloaded> downloaded;
    @Relationship(type = "Engage", direction = Relationship.INCOMING)
    private Set<Engage> engage;
    @Relationship(type = "HavingWl", direction = Relationship.INCOMING)
    private Set<HavingWl> havingWl;
    @Relationship(type = "HavingPf", direction = Relationship.INCOMING)
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


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", nric='" + nric + '\'' +
                '}';
    }
}
