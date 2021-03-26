package com.virtualdevice.levelsensor.service;

public interface DeviceService {

    void generateData(boolean activationFlag);

    Object getCurrentStatus();

    Object setLocation(String latitude, String longitude);

    Object getLocation();
}
