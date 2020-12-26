package com.packt.quarkus.chapter3;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/getContainerId")
public class HelloOKD {

    @Inject
    ContainerService containerService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "You are running on "  + containerService.getContainerId();
    }
}