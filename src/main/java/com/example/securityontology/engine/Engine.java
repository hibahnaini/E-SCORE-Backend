package com.example.securityontology.engine;

import com.example.securityontology.ontology.OntologyController;
/**
 *
 * Create a token on the jwt website using a specific issuer and a secret
 */
public class Engine {
    private final String ISSUER = "<add the issuer of the token>";
    private final String ApiSecret ="<add the secret used to create the token>";

    public String getISSUER() {
        return ISSUER;
    }

    public String getApiSecret() {
        return ApiSecret;
    }

}
