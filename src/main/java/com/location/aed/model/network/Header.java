package com.location.aed.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header {
    // api 통신시간
    private String transactionTime;

    private String resultCode;

    private String description;
}
