package org.proyect.infraestructure.output.adapter;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "currency-api")
public interface CurrencyService {
    @GET
    @Path("/{ip}/currency")
    @Produces(MediaType.TEXT_PLAIN)
    String getCurrencyCode(@PathParam("ip") String ip);
}
