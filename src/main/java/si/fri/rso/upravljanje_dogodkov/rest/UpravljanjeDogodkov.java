package si.fri.rso.upravljanje_dogodkov.rest;

import com.kumuluz.ee.discovery.annotations.DiscoverService;
import si.fri.rso.upravljanje_dogodkov.config.RestEndpoints;
import si.fri.rso.upravljanje_dogodkov.pojo.Dogodek;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Path("upravljanje_dogodkov")
public class UpravljanjeDogodkov {

    @Inject
    private RestEndpoints restEndpoints;

    @Inject
    @DiscoverService(value = "katalog_dogodkov-service", version = "1.0.x", environment = "dev")
    private WebTarget target;

    @GET
    public Response test() {
        return Response.ok(restEndpoints.getKatalogDogodkovUrl()).build();
    }

    @PUT
    @Path("/{dogodekId}")
    public Response editEvent(@PathParam("dogodekId") int dogodekId) {
        WebTarget service = target.path("v1/katalog_dogodkov");

        System.out.println("DELA");
        System.out.println(service.getUri().toString());

        Response katalogDogodkovResponse = ClientBuilder.newClient().target(service.getUri())
                .path(Integer.toString(dogodekId)).request().get();

        if(!katalogDogodkovResponse.getStatusInfo().getFamily().equals(Response.Status.Family.SUCCESSFUL)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Dogodek dogodek = katalogDogodkovResponse.readEntity(Dogodek.class);
        dogodek.setNazivDogodka("Domen Balantic");
        dogodek.setDatumDogodka(new Date());

        return ClientBuilder.newClient().target(service.getUri())
                .request().put(Entity.json(dogodek));

    }

}
