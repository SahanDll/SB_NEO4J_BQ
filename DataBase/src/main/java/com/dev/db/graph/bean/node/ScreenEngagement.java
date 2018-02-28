package com.dev.db.graph.bean.node;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.*;
import org.springframework.data.annotation.Id;

import java.util.Set;

@JsonIgnoreProperties(value = {"handler"})
@NodeEntity(label="ScreenEngagement")
public class ScreenEngagement {
    @GraphId
    @Id
    private Long id;
    @Property(name="screen")
    private String screen;
    @Transient
    private Long viewTime;
    @Transient
    private Long viewLength;

    @Relationship(type = "Engage", direction = Relationship.OUTGOING)
    private Set<User> user;

    public ScreenEngagement() {

    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public Long getViewTime() {
        return viewTime;
    }

    public void setViewTime(Long viewTime) {
        this.viewTime = viewTime;
    }

    public Long getViewLength() {
        return viewLength;
    }

    public void setViewLength(Long viewLength) {
        this.viewLength = viewLength;
    }

    @Override
    public String toString() {
        return "ScreenTransition{" +
                "id=" + id +
                ", screen='" + screen + '\'' +
                '}';
    }
}
