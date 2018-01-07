package si.fri.rso.upravljanje_dogodkov.rest;

import com.kumuluz.ee.discovery.annotations.RegisterService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@RegisterService
@ApplicationPath("v1")
public class UpravljanjeDogodkovApplication extends Application {
}
