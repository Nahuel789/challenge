package org.proyect.application.dto;

public class CurrencyDTO {

    private String currencyName;
    private String dollarQuote;

    public CurrencyDTO(String currencyName, String dollarQuote) {
        this.currencyName = currencyName;
        this.dollarQuote = dollarQuote;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getDollarQuote() {
        return dollarQuote;
    }

    public void setDollarQuote(String dollarQuote) {
        this.dollarQuote = dollarQuote;
    }
}
