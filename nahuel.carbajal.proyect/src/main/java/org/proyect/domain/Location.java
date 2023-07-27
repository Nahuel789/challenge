package org.proyect.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
    private int geonameId;
    private String capital;
    private List<Language> languages;
    private String countryFlag;
    private String countryFlagEmoji;
    private String countryFlagEmojiUnicode;
    private String callingCode;
    private boolean isEu;

    public int getGeonameId() {
        return geonameId;
    }

    public void setGeonameId(int geonameId) {
        this.geonameId = geonameId;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public String getCountryFlag() {
        return countryFlag;
    }

    public void setCountryFlag(String countryFlag) {
        this.countryFlag = countryFlag;
    }

    public String getCountryFlagEmoji() {
        return countryFlagEmoji;
    }

    public void setCountryFlagEmoji(String countryFlagEmoji) {
        this.countryFlagEmoji = countryFlagEmoji;
    }

    public String getCountryFlagEmojiUnicode() {
        return countryFlagEmojiUnicode;
    }

    public void setCountryFlagEmojiUnicode(String countryFlagEmojiUnicode) {
        this.countryFlagEmojiUnicode = countryFlagEmojiUnicode;
    }

    public String getCallingCode() {
        return callingCode;
    }

    public void setCallingCode(String callingCode) {
        this.callingCode = callingCode;
    }

    public boolean isEu() {
        return isEu;
    }

    public void setEu(boolean eu) {
        isEu = eu;
    }
}
