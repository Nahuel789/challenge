package org.proyect.infraestructure.input.adapter.http;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.proyect.application.StatisticsService;


@Path("/statistics")
@Consumes(MediaType.APPLICATION_JSON)
public class StatisticsController {
    @Inject
    private StatisticsService statisticsService;
    @GET
    @Path("/average-distance")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAverageStatistics() {
        return Response.status(Response.Status.OK).entity(statisticsService.getAverageDistance()).build();
    }
    @GET
    @Path("/greater-distance")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGreaterDistanceCountryInfo() {
        return Response.status(Response.Status.OK).entity(statisticsService.getGreaterDistanceStatistic()).build();
    }

    @GET
    @Path("/shorter-distance")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShorterDistanceCountryInfo() {
        return Response.status(Response.Status.OK).entity(statisticsService.getShorterDistanceStatistic()).build();

    }
}
