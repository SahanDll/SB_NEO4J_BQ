package com.dev.db.data.graph.bean.node;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.*;
import org.springframework.data.annotation.Id;

import java.util.Set;

@JsonIgnoreProperties(value = {"handler"})
@NodeEntity(label="GeoInfo")
public class GeoInfo {
    @GraphId
    @Id
    private Long id;
    @Property(name="continent")
    private String continent;
    @Property(name="country")
    private String country;
    @Property(name="region")
    private String region;
    @Property(name="city")
    private String city;

    @Relationship(type = "Login", direction = Relationship.OUTGOING)
    private Set<User> user;

    public GeoInfo(){

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

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "GeoInfo{" +
                "id=" + id +
                ", continent='" + continent + '\'' +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
