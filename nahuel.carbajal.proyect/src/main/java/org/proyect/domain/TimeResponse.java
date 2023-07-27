package org.proyect.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeResponse {
    private String currentLocalTime;
    public String getCurrentLocalTime() {
        return currentLocalTime;
    }
}
