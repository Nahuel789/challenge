package org.proyect.infraestructure.output.port;

import jakarta.ws.rs.QueryParam;
import org.proyect.domain.QuoteResponse;

public interface QuoteServicePort {
    QuoteResponse getExchangeRate(@QueryParam("access_key") String accessKey,
                                       @QueryParam("base") String baseCurrency,
                                       @QueryParam("symbols") String symbols);
}
