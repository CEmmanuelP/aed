package com.location.aed.controller;

import com.location.aed.model.entity.*;


import com.location.aed.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api")
public class GetController {

    private final FileService fileService;

    public GetController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/aed/json/{wgs84lat}/{wgs84lon}/{meter}")
    public AedResponseDto getAeds(@PathVariable double wgs84lat, @PathVariable double wgs84lon, @PathVariable float meter) {


        ArrayList<Aed> aed;
        int count = 0;

        ArrayList<SortedAed> aedResponse = new ArrayList<>();

        aed = fileService.createAedArray();

        for (int i = 0; i < aed.size(); i++) {
            double distance = calculateDistance(wgs84lat, wgs84lon, aed.get(i).getWgs84lat(), aed.get(i).getWgs84lon());

            if (distance <= meter) {
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

    @GetMapping("/shelter/json/{lat}/{lng}/{meter}")
    public ShelterResponseDto getShelters(@PathVariable double lat, @PathVariable double lng, @PathVariable float meter) {


        ArrayList<Shelter> shelters;
        int count = 0;

        ArrayList<SortedShelter> shelterResponse = new ArrayList<>();

        shelters = fileService.createShelterArray();

        for (int i = 0; i < shelters.size(); i++) {

            String stringLat = shelters.get(i).getLat();
            String stringLng = shelters.get(i).getLng();

            double shelterLat = Double.parseDouble(stringLat);
            double shelterLng = Double.parseDouble(stringLng);

            double distance = calculateDistance(lat, lng, shelterLat, shelterLng);

            if (distance <= meter) {
                shelterResponse.add(SortedShelter.builder()
                        .distance(distance)
                        .shelter(shelters.get(i))
                        .build());

                count++;
            }
        }

        Collections.sort(shelterResponse);

        return ShelterResponseDto.builder()
                .count(count)
                .sortedShelters(shelterResponse)
                .build();
    }


    public double calculateDistance(double inputLat, double inputLon, double aedLat, double aedLon) {

        return (6371 * Math.acos(Math.cos(Math.toRadians(inputLat)) * Math.cos(Math.toRadians(aedLat)) * Math.cos(Math.toRadians(aedLon) -
                Math.toRadians(inputLon)) + Math.sin(Math.toRadians(inputLat)) * Math.sin(Math.toRadians(aedLat))));
    }
}
