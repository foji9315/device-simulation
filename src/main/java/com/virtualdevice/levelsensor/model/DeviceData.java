package com.virtualdevice.levelsensor.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceData {

    private Double level;
    private String timeStamp;
    private String longitude;
    private String latitude;
}
