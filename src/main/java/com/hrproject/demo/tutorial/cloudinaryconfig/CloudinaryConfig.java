package com.hrproject.demo.tutorial.cloudinaryconfig;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Value("${application.cloudinary.cloud_name}")
    private String CLOUD_NAME;
    @Value("${application.cloudinary.Api_key}")
    private String API_KEY;
    @Value("${application.cloudinary.Api_secret}")
    private String API_SECRET;

    public Cloudinary cloudinaryConfig(){
        Map<String, String> config = new HashMap<>();
        config.put("api_key", API_KEY);
        config.put("api_secret", API_SECRET);
        config.put("cloud_name", CLOUD_NAME);
        return new Cloudinary(config);
    }

}
