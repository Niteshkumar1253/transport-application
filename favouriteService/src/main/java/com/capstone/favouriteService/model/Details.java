package com.capstone.favouriteService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Details {
    private String source;
    private String destination;
    private String srcLon;
    private String srcLat;
    private String destLon;
    private String destLat;
}
