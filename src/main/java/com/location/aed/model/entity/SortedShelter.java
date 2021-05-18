package com.location.aed.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SortedShelter implements Comparable<SortedShelter>{
    private double distance;

    private Shelter shelter;

    @Override
    public int compareTo(SortedShelter sortedShelter) {
        int a = Integer.parseInt(String.valueOf(Math.round(this.distance * 100)));
        int b = Integer.parseInt(String.valueOf(Math.round(sortedShelter.distance * 100)));
        System.out.println(a);
        System.out.println(b);
        return Integer.compare(a, b);
    }
}
