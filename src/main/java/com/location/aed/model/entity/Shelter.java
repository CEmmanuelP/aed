package com.location.aed.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Shelter {
    private String placeName;

    private String shelterCategory;

    private String lat;

    private String lng;

    private String address;

    private String capacityArea;
}
