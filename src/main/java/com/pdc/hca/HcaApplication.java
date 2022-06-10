package com.pdc.hca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class HcaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HcaApplication.class, args);
    }

}
