package org.proyect.infraestructure.input.port;

import org.proyect.application.dto.CountryInfoDTO;

public interface GeoDataInputPort {
    CountryInfoDTO getCountryInfo(String ip);
}