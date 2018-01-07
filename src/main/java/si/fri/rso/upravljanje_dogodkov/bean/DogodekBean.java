package si.fri.rso.upravljanje_dogodkov.bean;

import com.kumuluz.ee.discovery.annotations.DiscoverService;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import si.fri.rso.upravljanje_dogodkov.pojo.Dogodek;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.Date;

@RequestScoped
public class DogodekBean {

    @Inject
    @DiscoverService(value = "katalog_dogodkov-service", version = "1.0.x", environment = "dev")
    private WebTarget target;

    @CircuitBreaker(requestVolumeThreshold = 2)
    @Fallback(fallbackMethod = "getDogodekFallback")
    @Timeout
    public Dogodek getDogodek(String dogodekId) {
            try {
                WebTarget service = target.path("v1/katalog_dogodkov");

                Response katalogDogodkovResponse = ClientBuilder.newClient().target(service.getUri())
                        .path(dogodekId).request().get();

                return katalogDogodkovResponse.readEntity(Dogodek.class);
            } catch (WebApplicationException e) {
                System.out.println(e);
            } catch (ProcessingException e) {
                System.out.println(e);
            }

            return null;
    }

    public Dogodek getDogodekFallback(String dogodekId) {

        Dogodek dogodek = new Dogodek();
        dogodek.setNazivDogodka("Fallback dogodek");
        dogodek.setDatumDogodka(new Date());
        dogodek.setId(Integer.parseInt(dogodekId));
        dogodek.setOpisDogodka("Preizkus delovanja fallback metode.");

        return dogodek;

    }

}
