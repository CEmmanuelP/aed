package com.location.aed.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SortedAed implements Comparable<SortedAed> {
    private double distance;

    private Aed aed;

    @Override
    public int compareTo(SortedAed sortedAed) {
        int a = Integer.parseInt(String.valueOf(Math.round(this.distance * 100)));
        int b = Integer.parseInt(String.valueOf(Math.round(sortedAed.distance * 100)));
        System.out.println(a);
        System.out.println(b);
        return (a < b) ? -1 : ((a == b) ? 0 : 1);
    }
}
