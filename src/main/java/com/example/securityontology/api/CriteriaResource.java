package com.example.securityontology.api;

import com.example.securityontology.ontology.OntologyController;
import org.json.simple.JSONArray;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("criteria")
public class CriteriaResource {
    //OntologyController api = new OntologyController();

    /**
     *
     * @return all security criteria
     */
    @GET
    @Main.Secured
    @Produces(MediaType.APPLICATION_JSON)
    public String getCriteria() {
        // here you can return any bean also it will automatically convert into json
        return JSONArray.toJSONString(Main.getApi().getAllCriteria());
    }

    @GET
    @Main.Secured
    @Path("{relation}/{criteria}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCriteriaByRelation(@PathParam("relation") String relation,@PathParam("criteria") String criteria) {
        return JSONArray.toJSONString(Main.getApi().getCriteriaByRelation(relation,criteria));
    }


    @GET
    @Main.Secured
    @Path("{criteria}/mechanisms")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCriteriaMechanisms(@PathParam("criteria") String criteria) {
        return JSONArray.toJSONString(Main.getApi().getCriteriaMechanisms(criteria));
    }


}
