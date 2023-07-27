package org.proyect.infraestructure.output.adapter;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.proyect.domain.CountryInfo;

@RegisterRestClient(configKey = "ip2country-api")
public interface GeolocationService {
        @GET
        @Path("/{ip}")
        @Produces(MediaType.APPLICATION_JSON)
        CountryInfo getCountryInfo(@PathParam("ip") String ip, @QueryParam("access_key") String accessToken);
}
