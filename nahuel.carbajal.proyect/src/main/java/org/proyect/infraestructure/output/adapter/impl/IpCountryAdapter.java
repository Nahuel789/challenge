package org.proyect.infraestructure.output.adapter.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.proyect.domain.CountryInfo;
import org.proyect.infraestructure.output.adapter.GeolocationService;
import org.proyect.infraestructure.output.port.IpCountryServicePort;

@ApplicationScoped
public class IpCountryAdapter implements IpCountryServicePort {

    @ConfigProperty(name = "country-info-access-token")
    String accessToken;

    @Inject
    @RestClient
    private GeolocationService geolocationService;

    @Override
    public CountryInfo getCountryInfo(String ip){
            return geolocationService.getCountryInfo(ip, accessToken);
    }
}
