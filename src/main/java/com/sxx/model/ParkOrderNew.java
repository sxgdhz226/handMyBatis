package com.sxx.model;

import lombok.Data;

import java.util.Date;

@Data
public class ParkOrderNew {
    private String id;
    private String inOutId;
    private String payTime;
    private String payAmount;
    private Date enterAt;
    private Date createdAt;
}
