package org.proyect.application.dto;

import java.util.List;

public class CountryInfoDTO {
    private String ip;
    private String countryName;
    private List<String> languages;

    private List<String> time;
    private String estimatedDistanceFromBuenosAiresToCountry;
    private CurrencyDTO currencyDTO;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public String getEstimatedDistanceFromBuenosAiresToCountry() {
        return estimatedDistanceFromBuenosAiresToCountry;
    }

    public void setEstimatedDistanceFromBuenosAiresToCountry(String estimatedDistanceFromBuenosAiresToCountry) {
        this.estimatedDistanceFromBuenosAiresToCountry = estimatedDistanceFromBuenosAiresToCountry;
    }

    public CurrencyDTO getCurrency() {
        return currencyDTO;
    }

    public void setCurrency(CurrencyDTO currencyDTO) {
        this.currencyDTO = currencyDTO;
    }
}
