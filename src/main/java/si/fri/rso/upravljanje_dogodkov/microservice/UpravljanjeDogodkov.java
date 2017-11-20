package si.fri.rso.upravljanje_dogodkov.microservice;

import si.fri.rso.upravljanje_dogodkov.pojo.Dogodek;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Path("upravljanje_dogodkov")
public class UpravljanjeDogodkov {

    @PUT
    @Path("/{dogodekId}")
    public Response editEvent(@PathParam("dogodekId") int dogodekId) {

        System.out.println("DELA");

        Response katalogDogodkovResponse = ClientBuilder.newClient().target("http://localhost:8080/v1/")
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
