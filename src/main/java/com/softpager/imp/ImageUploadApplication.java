package com.softpager.imp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ImageUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImageUploadApplication.class, args);
    }

}

