package com.example.securityontology.ontology;

import org.apache.jena.base.Sys;

public class Main {
    public static void main(String[] args)
    {
       OntologyController ont=new OntologyController();
        System.out.println( ont.getAllDomains());
        System.out.println( ont.getAllCriteria());
        System.out.println( ont.getAllMechanisms());
        System.out.println( ont.getMechanismsByDomain("Banking"));
        System.out.println( ont.getCriteriaByDomain("Banking"));
        System.out.println( ont.getCriteriaMechanismsByDomain("Banking","Authentication"));
        System.out.println( ont.getCriteriaByRelationByDomain("Banking","AccessControl","ensures"));
        System.out.println( ont.getCriteriaByRelation("Privacy","ensures"));
        System.out.println( ont.getAdditionalCriteriaByRelationByDomain("Banking","ensures"));
        System.out.println( ont.sparqlQuery(
                "CONSTRUCT  WHERE { ?s :belongsTo :Transportation}"));
        System.out.println(ont.updateQuery(
                "INSERT DATA\n" +
                        "{ :SC1 rdf:type :SecurityCriterion}\n"
                        ));
        System.out.println(ont.updateQuery(
                "INSERT DATA\n" +
                        "{ :SC2 rdf:type :SecurityCriterion}\n"
        ));
        System.out.println(ont.updateQuery(
                "INSERT DATA\n" +
                        "{ :SC1 :ensures :SC2}\n"
        ));
        System.out.println( ont.getAllCriteria());
        System.out.println(ont.getCriteriaByRelation("SC2","ensures"));
        /*System.out.println(ont.updateQuery(
                "DELETE DATA\n" +
                        "{ :SC1 rdf:type :SecurityCriterion}\n"
        ));*/
        System.out.println( ont.getAllCriteria());
        System.out.println(ont.updateQuery(
                        "DELETE { ?sc :ensures :SC2 }\n" +
                        "INSERT { ?sc :ensures :Trust }\n" +
                        "WHERE\n" +
                        "  { ?sc :ensures :SC2\n" +
                        "  } \n"
        ));
        System.out.println( ont.getAllCriteria());
        System.out.println(ont.getCriteriaByRelation("SC2","ensures"));
        System.out.println(ont.getCriteriaByRelation("Trust","ensures"));
    }
}
