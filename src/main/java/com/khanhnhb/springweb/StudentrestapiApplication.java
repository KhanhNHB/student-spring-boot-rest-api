package com.khanhnhb.springweb;

import com.khanhnhb.springweb.config.FileStorageConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageConfiguration.class})
public class StudentrestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentrestapiApplication.class, args);
	}

}
