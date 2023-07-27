package org.proyect.infraestructure.output.adapter;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.proyect.domain.TimeResponse;

@RegisterRestClient(configKey = "world-time-api")
public interface TimeZoneService {
    @GET
    TimeResponse getWorldTime(@QueryParam("ipAddress") String ipAddress);
}
