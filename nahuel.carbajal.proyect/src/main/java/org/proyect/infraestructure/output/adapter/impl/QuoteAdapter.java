package org.proyect.infraestructure.output.adapter.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.proyect.domain.QuoteResponse;
import org.proyect.infraestructure.output.adapter.QuoteService;
import org.proyect.infraestructure.output.port.QuoteServicePort;

@ApplicationScoped
public class QuoteAdapter implements QuoteServicePort {

    @Inject
    @RestClient
    private QuoteService quoteService;

    @Override
    public QuoteResponse getExchangeRate(String accessKey, String baseCurrency, String symbol) {
        return quoteService.getExchangeRate(accessKey, baseCurrency, symbol);
    }
}
