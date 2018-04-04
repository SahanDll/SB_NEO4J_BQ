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
@NodeEntity(label="Notification")
public class Notification {
    @GraphId
    @Id
    private Long id;
    @Property(name="type")
    private String type;

    @Relationship(type = "Open", direction = Relationship.OUTGOING)
    private Set<User> user;

    public Notification() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
