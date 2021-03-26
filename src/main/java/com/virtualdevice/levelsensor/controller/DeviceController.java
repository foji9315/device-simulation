package com.virtualdevice.levelsensor.controller;

import com.virtualdevice.levelsensor.service.impl.DeviceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/poc/device")
public class DeviceController {

    @Autowired
    private DeviceServiceImpl deviceService;

    @GetMapping("")
    public Object sendData(@RequestParam boolean activate) {
        deviceService.generateData(activate);
        return "Starting simulation";
    }

    @GetMapping("/status")
    public Object getCurrentStatus() {
        return deviceService.getCurrentStatus();
    }

    @GetMapping("/location/change")
    public Object setCurrentLocation(@RequestParam String lat, @RequestParam String lon) {
        return deviceService.setLocation(lat, lon);
    }

    @GetMapping("/location")
    public Object getCurrentLocation() {
        return deviceService.getLocation();
    }
}
