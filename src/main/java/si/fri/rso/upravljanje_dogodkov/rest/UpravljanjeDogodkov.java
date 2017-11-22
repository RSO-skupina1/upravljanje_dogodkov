package si.fri.rso.upravljanje_dogodkov.rest;

import si.fri.rso.upravljanje_dogodkov.config.RestEndpoints;
import si.fri.rso.upravljanje_dogodkov.pojo.Dogodek;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Path("upravljanje_dogodkov")
public class UpravljanjeDogodkov {

    @Inject
    private RestEndpoints restEndpoints;

    @GET
    public Response test() {
        return Response.ok(restEndpoints.getKatalogDogodkovUrl()).build();
    }

    @PUT
    @Path("/{dogodekId}")
    public Response editEvent(@PathParam("dogodekId") int dogodekId) {

        System.out.println("DELA");

        Response katalogDogodkovResponse = ClientBuilder.newClient().target(restEndpoints.getKatalogDogodkovUrl())
                .path("katalog_dogodkov").path(Integer.toString(dogodekId)).request().get();

        if(!katalogDogodkovResponse.getStatusInfo().getFamily().equals(Response.Status.Family.SUCCESSFUL)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Dogodek dogodek = katalogDogodkovResponse.readEntity(Dogodek.class);
        dogodek.setDatumDogodka(new Date());

        return ClientBuilder.newClient().target("http://localhost:8080/v1/")
                .path("katalog_dogodkov").request().put(Entity.json(dogodek));

    }

}
