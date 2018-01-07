package si.fri.rso.upravljanje_dogodkov.rest;

import com.kumuluz.ee.discovery.annotations.DiscoverService;
import si.fri.rso.upravljanje_dogodkov.bean.DogodekBean;
import si.fri.rso.upravljanje_dogodkov.config.ConfigurationData;
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
    @DiscoverService(value = "katalog_dogodkov-service", version = "1.0.x", environment = "dev")
    private WebTarget target;

    @Inject
    private DogodekBean dogodekBean;

    @Inject
    private ConfigurationData configurationData;

    @GET
    @Path("/test")
    public Response test() {
        return Response.ok(configurationData.toString()).build();
    }

    @GET
    @Path("/{dogodekId}")
    public Response getEvent(@PathParam("dogodekId") int dogodekId) {
        Dogodek dogodek = dogodekBean.getDogodek(Integer.toString(dogodekId));

        if(dogodek == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(dogodek).build();

    }

    @PUT
    @Path("/{dogodekId}")
    public Response editEvent(@PathParam("dogodekId") int dogodekId) {
        WebTarget service = target.path("v1/katalog_dogodkov");

        Dogodek dogodek = dogodekBean.getDogodek(Integer.toString(dogodekId));

        if(dogodek == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        dogodek.setNazivDogodka(configurationData.getNazivDogodka());
        dogodek.setDatumDogodka(new Date());

        return ClientBuilder.newClient().target(service.getUri())
                .request().put(Entity.json(dogodek));
    }

}
