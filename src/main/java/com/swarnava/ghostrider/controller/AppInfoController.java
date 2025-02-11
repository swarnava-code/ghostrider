package com.swarnava.ghostrider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AppInfoController {
    @Value("${app.version}")
    private String appVersion;

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/version")
    public Map<String, String> getVersion(){
        return Map.of("version", appVersion);
    }

    @GetMapping("/appname")
    public Map<String, String> getAppName(){
        return Map.of("appName", appName);
    }

    @GetMapping("/info")
    public Map<String, String> getAppInfo(){
        return Map.of(
                "appVersion", appVersion,
                "appName", appName
        );
    }

}
