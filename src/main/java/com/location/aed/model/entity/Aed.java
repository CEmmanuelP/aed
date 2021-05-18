package com.location.aed.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Aed {

    private String model;

    // 제조사
    private String mfg;

    private String zipcode1;

    private String zipcode2;

    private String manager;

    private String managertel;

    private String org;

    private String buildaddress;

    // 설치기관 전화번호
    private String clerktel;

    private String buildplace;

    private double wgs84lon;

    private double wgs84lat;

}
