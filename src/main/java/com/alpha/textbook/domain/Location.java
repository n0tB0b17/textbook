package com.alpha.textbook.domain;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node("Location")
public class Location {
    @Id
    @GeneratedValue
    private Long id;

    @Property("city")
    private String city;

    @Property("state")
    private String state;

    @Property("country")
    private String country;

    @Property("postalCode")
    private String postalCode;

    @Property("latitude")
    private Double latitude;

    @Property("longitude")
    private Double longitude;

    public Location(String city, String state, String country, String postalCode, Double latitude, Double longitude) {
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
