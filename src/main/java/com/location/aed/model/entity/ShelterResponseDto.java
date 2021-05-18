package com.location.aed.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShelterResponseDto {
    private int count;
    private List<SortedShelter> sortedShelters;
}
