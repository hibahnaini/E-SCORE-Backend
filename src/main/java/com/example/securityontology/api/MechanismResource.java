package com.example.securityontology.api;


import com.example.securityontology.ontology.OntologyController;
import org.json.simple.JSONArray;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("mechanism")
public class MechanismResource {
    //OntologyController api = new OntologyController();

    /**
     *
     * @return all security mechanisms
     */
    @GET
    @Main.Secured
    @Produces(MediaType.APPLICATION_JSON)
    public String getMechanisms() {
        // here you can return any bean also it will automatically convert into json
        return JSONArray.toJSONString(Main.getApi().getAllMechanisms());
    }


}
