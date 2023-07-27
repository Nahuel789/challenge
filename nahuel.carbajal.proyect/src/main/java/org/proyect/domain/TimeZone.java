package org.proyect.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TimeZone {

    private List<String> currentTime;
    private int gmtOffset;
    private String code;
    private boolean isDaylightSaving;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(List<String> currentTime) {
        this.currentTime = currentTime;
    }

    public int getGmtOffset() {
        return gmtOffset;
    }

    public void setGmtOffset(int gmtOffset) {
        this.gmtOffset = gmtOffset;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isDaylightSaving() {
        return isDaylightSaving;
    }

    public void setDaylightSaving(boolean daylightSaving) {
        isDaylightSaving = daylightSaving;
    }
}
