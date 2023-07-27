package org.proyect.infraestructure.output.adapter.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.proyect.infraestructure.output.adapter.CurrencyService;
import org.proyect.infraestructure.output.port.CurrencyServicePort;

@ApplicationScoped
public class CurrencyAdapter implements CurrencyServicePort {

    @Inject
    @RestClient
    private CurrencyService currencyService;

    @Override
    public String getCurrencyCode(String ip) {
        return currencyService.getCurrencyCode(ip);
    }
}
