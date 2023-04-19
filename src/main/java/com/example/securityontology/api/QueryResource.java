package com.example.securityontology.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/query")
public class QueryResource {
    @POST
    @Main.Secured
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public String sparqlQuery(String query) {
        return JSONObject.toJSONString(Main.getApi().sparqlQuery(query));
    }

    @POST
    @Main.Secured
    @Path("update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public String updateQuery(String query) {
        return JSONObject.toJSONString(Main.getApi().updateQuery(query));
    }
}
