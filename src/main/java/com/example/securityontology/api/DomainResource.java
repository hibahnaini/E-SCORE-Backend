package com.example.securityontology.api;


import com.example.securityontology.ontology.OntologyController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/domain")
public class DomainResource {
//OntologyController api = new OntologyController();

    /**
     *
     * @return all domains
     */
    @GET
    @Main.Secured
    @Produces(MediaType.APPLICATION_JSON)
    public String getDomains() {
        // here you can return any bean also it will automatically convert into json
        return JSONArray.toJSONString(Main.getApi().getAllDomains());
    }

    /**
     *
     * @param domain
     * @return all security mechanisms in a domain
     */
    @GET
    @Main.Secured
    @Path("{domain}/mechanisms")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMechanismsByDomain(@PathParam("domain") String domain) {
        return JSONArray.toJSONString(Main.getApi().getMechanismsByDomain(domain));
    }

    /**
     *
     * @param domain
     * @return all security criteria in a domain
     */
    @GET
    @Main.Secured
    @Path("{domain}/criteria")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCriteriaByDomain(@PathParam("domain") String domain) {
        return JSONArray.toJSONString(Main.getApi().getCriteriaByDomain(domain));
    }

    /**
     *
     * @param domain
     * @param criteria
     * @return security mechanisms of a security criterion in a domain
     */
    @GET
    @Main.Secured
    @Path("{domain}/{criteria}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCriteriaMechanismsByDomain(@PathParam("domain") String domain,@PathParam("criteria") String criteria) {
        return JSONArray.toJSONString(Main.getApi().getCriteriaMechanismsByDomain(domain,criteria));
    }

    /**
     *
     * @param domain
     * @param criteria
     * @param relation
     * @return security criteria that have a given relation to a given criteria in a given domain
     */
    @GET
    @Main.Secured
    @Path("{domain}/{relation}/{criteria}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCriteriaByRelationByDomain(@PathParam("domain") String domain,@PathParam("criteria") String criteria, @PathParam("relation") String relation) {
        return JSONArray.toJSONString(Main.getApi().getCriteriaByRelationByDomain(domain,criteria,relation));
    }

    @GET
    @Main.Secured
    @Path("{domain}/{relation}/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAdditionalCriteriaByRelationByDomain(@PathParam("domain") String domain, @PathParam("relation") String relation) {
        return JSONObject.toJSONString(Main.getApi().getAdditionalCriteriaByRelationByDomain(domain,relation));
    }

}
