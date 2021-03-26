package com.virtualdevice.levelsensor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class WaterTankLevelSensorApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaterTankLevelSensorApplication.class, args);
	}

}
