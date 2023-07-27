package org.proyect.infraestructure.input.adapter.http;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.proyect.application.GeolocationService;
import org.proyect.application.StatisticsService;
import org.proyect.application.dto.CountryInfoDTO;
import org.proyect.domain.Statistic;
import org.proyect.infraestructure.input.adapter.http.advice.exception.InvalidIpException;
import org.proyect.infraestructure.input.adapter.http.utils.IpValidator;

@Path("/geolocation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GeolocationController {
    @Inject
    private GeolocationService geolocationService;

    @Inject
    private StatisticsService statisticsService;

    @GET
    @Path("/{ip}")
    public Response getIpInfo(@PathParam("ip") String ip) throws InvalidIpException,Exception {
        IpValidator.validateIp(ip);
        CountryInfoDTO countryInfo = geolocationService.getCountryInfo(ip);
        statisticsService.
                saveStatistics(
                        new Statistic(
                                countryInfo.getCountryName(),
                                Integer.parseInt(countryInfo.getEstimatedDistanceFromBuenosAiresToCountry().split("\\s+")[0])));
        return Response.status(Response.Status.OK).entity(countryInfo).build();
    }
}