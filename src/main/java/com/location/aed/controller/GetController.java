package com.location.aed.controller;

import com.location.aed.model.entity.Aed;


import com.location.aed.model.entity.AedResponseDto;
import com.location.aed.model.entity.SortedAed;
import com.location.aed.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class GetController {

    private final FileService fileService;

    ArrayList<Aed> aed = new ArrayList<>();

    private String AUTHORIZE_KEY = "42434362536368723534707073524e";

    public GetController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/storesByGeo/json/{wgs84lat}/{wgs84lon}/{meter}")
    public AedResponseDto getAeds(@PathVariable double wgs84lat, @PathVariable double wgs84lon, @PathVariable float meter){


        ArrayList<Aed> aed;
        int count = 0;

        ArrayList<SortedAed> aedResponse = new ArrayList<>();

        aed = fileService.createAedArray();

        for(int i = 0; i < aed.size(); i++){
            double distance = calculateDistance(wgs84lat, wgs84lon, aed.get(i).getWgs84lat(), aed.get(i).getWgs84lon());

            if(distance <= meter){
                aedResponse.add(SortedAed.builder()
                        .distance(distance)
                        .aed(aed.get(i))
                        .build());

                count++;
            }
        }

        Collections.sort(aedResponse);

        return AedResponseDto.builder()
                .count(count)
                .sortedAeds(aedResponse)
                .build();
    }



    public double calculateDistance(double inputLat, double inputLon, double aedLat, double aedLon){

        return (6371*Math.acos(Math.cos(Math.toRadians(inputLat))*Math.cos(Math.toRadians(aedLat))*Math.cos(Math.toRadians(aedLon) -
                Math.toRadians(inputLon))+Math.sin(Math.toRadians(inputLat))*Math.sin(Math.toRadians(aedLat))));
    }
}
