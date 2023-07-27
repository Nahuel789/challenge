package org.proyect.infraestructure.output.adapter.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.proyect.domain.TimeResponse;
import org.proyect.infraestructure.output.adapter.TimeZoneService;
import org.proyect.infraestructure.output.port.TimeZoneServicePort;

@ApplicationScoped
public class TimeZoneAdapter implements TimeZoneServicePort {

    @Inject
    @RestClient
    private TimeZoneService timeZoneService;

    @Override
    public String getWorldTime(String ipAddress) {
        TimeResponse timeResponse = timeZoneService.getWorldTime(ipAddress);

        return timeResponse.getCurrentLocalTime();
    }
}