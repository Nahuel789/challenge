package org.proyect.infraestructure.output.adapter;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.proyect.domain.QuoteResponse;

@RegisterRestClient(configKey = "quote-api")
public interface QuoteService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    QuoteResponse getExchangeRate(@QueryParam("access_key") String accessKey,
                                       @QueryParam("base") String baseCurrency,
                                       @QueryParam("symbols") String symbol);
}
