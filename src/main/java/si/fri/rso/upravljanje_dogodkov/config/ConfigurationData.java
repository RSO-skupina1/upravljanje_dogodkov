package si.fri.rso.upravljanje_dogodkov.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("rest-endpoints")
public class ConfigurationData {

    @ConfigValue(watch = true)
    private String nazivDogodka;

    public String getNazivDogodka() {
        return nazivDogodka;
    }

    @Override
    public String toString() {
        return "ConfigurationData{" +
                "nazivDogodka='" + nazivDogodka + '\'' +
                '}';
    }
}
