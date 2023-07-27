package org.proyect.infraestructure.input.adapter.http.advice.handler;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.proyect.infraestructure.input.adapter.http.advice.exception.InvalidIpException;

@Provider
public class InvalidIpExceptionHandler implements ExceptionMapper<InvalidIpException> {

    @Override
    public Response toResponse(InvalidIpException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(exception.getMessage())
                .build();
    }
}
