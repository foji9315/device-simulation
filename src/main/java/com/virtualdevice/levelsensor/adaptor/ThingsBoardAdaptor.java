package com.virtualdevice.levelsensor.adaptor;

import com.virtualdevice.levelsensor.exception.BadRequestException;
import com.virtualdevice.levelsensor.model.DeviceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ThingsBoardAdaptor {

    private static final String PROBLEM_SENDING_DATA = "Send data to ThingsBoard had an error with description %s";
    private static final String TELEMETRY = "/telemetry";

    @Autowired
    private RestTemplate restTemplate;

    @Value("${thingsBoard.url}")
    private String baseUrl;

    @Value("${thingsBoard.token}")
    private String token;

    public Object postDeviceData(DeviceData deviceData) {
        String url = getThingsBoardUrl();
        ResponseEntity<Object> serviceResponse = null;
        try {
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> request = new HttpEntity<>(deviceData, header);
            serviceResponse = restTemplate.exchange(url, HttpMethod.POST, request, Object.class);
            return serviceResponse;
        } catch (Exception e) {
            throw new BadRequestException(String.format(PROBLEM_SENDING_DATA, e.getMessage()));
        }
    }

    private String getThingsBoardUrl() {
        return baseUrl + token + TELEMETRY;
    }
}
