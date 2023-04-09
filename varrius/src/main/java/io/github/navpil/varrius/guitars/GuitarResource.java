package io.github.navpil.varrius.guitars;

import io.github.navpil.guitars.GuitarService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("guitars")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class GuitarResource {

    private final GuitarService service;

    @Inject
    public GuitarResource(GuitarService service) {
        this.service = service;
    }
    @GET
    @Path("list")
    public Response list() {
        List<String> users = service.list();
        return Response.ok(users).build();
    }


}
