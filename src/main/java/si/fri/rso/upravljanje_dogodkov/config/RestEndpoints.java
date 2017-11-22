package si.fri.rso.upravljanje_dogodkov.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("rest-endpoints")
public class RestEndpoints {

    @ConfigValue(watch = true)
    private String katalogDogodkovUrl;

    public String getKatalogDogodkovUrl() {
        return katalogDogodkovUrl;
    }

    public void setKatalogDogodkovUrl(String katalogDogodkovUrl) {
        this.katalogDogodkovUrl = katalogDogodkovUrl;
    }
}
