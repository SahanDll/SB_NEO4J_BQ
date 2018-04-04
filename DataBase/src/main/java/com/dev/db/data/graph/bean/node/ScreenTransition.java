package com.dev.db.data.graph.bean.node;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.*;
import org.springframework.data.annotation.Id;

import java.util.Set;

@JsonIgnoreProperties(value = {"handler"})
@NodeEntity(label="ScreenTransition")
public class ScreenTransition {
    @GraphId
    @Id
    private Long id;
    @Property(name="previous_screen")
    private String previousScreen;
    @Property(name="screen")
    private String screen;

    @Relationship(type = "View", direction = Relationship.OUTGOING)
    private Set<User> user;

    public ScreenTransition() {

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

    public String getPreviousScreen() {
        return previousScreen;
    }

    public void setPreviousScreen(String previousScreen) {
        this.previousScreen = previousScreen;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }



    @Override
    public String toString() {
        return "ScreenTransition{" +
                "id=" + id +
                ", previousScreen='" + previousScreen + '\'' +
                ", screen='" + screen + '\'' +
                '}';
    }
}
