package com.alpha.textbook.dto.Location;

import lombok.Data;

@Data
public class CreateLocationRequest {
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private Double latitude;
    private Double longitude;
}
