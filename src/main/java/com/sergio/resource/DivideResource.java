package com.sergio.resource;

import com.sergio.service.DivideService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.math.BigDecimal;

@Path("/divide")
public class DivideResource {

    @Inject
    DivideService divideService;

    @GET
    @Path("/{dividend}/{divisor}")
    @Produces(MediaType.TEXT_PLAIN)
    public BigDecimal divide(int dividend, int divisor) {
        return divideService.divide(dividend, divisor);
    }


}
