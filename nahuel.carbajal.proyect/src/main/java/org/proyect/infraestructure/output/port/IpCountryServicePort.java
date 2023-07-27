package org.proyect.infraestructure.output.port;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.proyect.domain.CountryInfo;

public interface IpCountryServicePort {
    CountryInfo getCountryInfo(String ip) throws JsonProcessingException;
}
