package com.virtualdevice.levelsensor.service.impl;

import com.virtualdevice.levelsensor.adaptor.ThingsBoardAdaptor;
import com.virtualdevice.levelsensor.exception.BadRequestException;
import com.virtualdevice.levelsensor.model.DeviceData;
import com.virtualdevice.levelsensor.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class DeviceServiceImpl implements DeviceService {

    private static final int max = 100;
    private static final int min = 0;
    private static final String RESPONSE_STATUS = "The current status for simulation data is %s";
    private static final String RESPONSE_LOCATION = "The current lcation is lat: %s , lon: %s";

    private static final String INSTRUCTIONS = "To activate simulation please click in: " +
            " <a href='.?activate=true'> Activate </a> <br>" +
            "To look current status click here: " +
            "<a href='./status'> Status </a> </br>" +
            "To change location go to ./poc/device/location/change?lat={value}&lon={value} <br>" +
            "To look current location click here: " +
            "<a href='./location'> Location </a> </br>";

    private static final String ACTIVE= "ACTIVE";
    private static final String INACTIVE= "INACTIVE";

    private static String longitude = "-99.1920792";
    private static String latitude = "19.3313681";
    private static boolean asyncEnable = false;

    @Autowired
    private ThingsBoardAdaptor thingsBoardAdaptor;

    @Override
    public void generateData(boolean activationFlag) {
        runSimulation(activationFlag);
    }

    @Override
    public Object getCurrentStatus() {
        return getStatusOfSimulation();
    }

    @Override
    public Object setLocation(String latitude, String longitude) {
        DeviceServiceImpl.latitude = latitude;
        DeviceServiceImpl.longitude = longitude;
        return getCurrentLocation();
    }

    @Override
    public Object getLocation() {
        return getCurrentLocation();
    }

    @Override
    public Object getInstructions() {
        return INSTRUCTIONS;
    }

    @Async
    public void runSimulation(boolean activationFlag) {
        asyncEnable = activationFlag;
        try{
            while(asyncEnable) {
                String timeStamp = LocalTime.now().plusSeconds(5).format(DateTimeFormatter.ISO_TIME);
                double percentage = Math.random() * (max - min + 1) + min;
                DeviceData deviceData = new DeviceData(percentage, timeStamp, longitude, latitude);
                thingsBoardAdaptor.postDeviceData(deviceData);
            }
        } catch (Exception ex){
            throw new BadRequestException("We got an exception: " + ex);
        }

    }

    private String getStatusOfSimulation(){
        return String.format(RESPONSE_STATUS,asyncEnable ? ACTIVE : INACTIVE);
    }

    private String getCurrentLocation(){
        return String.format(RESPONSE_LOCATION,DeviceServiceImpl.latitude,DeviceServiceImpl.longitude);
    }
}
