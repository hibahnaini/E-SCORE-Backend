package com.example.securityontology.api;

import com.example.securityontology.auth.Auth;
import com.example.securityontology.ontology.OntologyController;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.NameBinding;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Application;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.HashSet;
import java.util.Set;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@ApplicationPath("/api")
public class Main extends Application {
    @NameBinding
    @Retention(RUNTIME)
    @Target({TYPE, METHOD})
    public @interface Secured { }
    public static OntologyController api = new OntologyController();

    public static OntologyController getApi() {
        return api;
    }


}