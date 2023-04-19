package com.example.securityontology.ontology;



import org.apache.jena.ontology.OntModel;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateAction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Iterator;
import java.util.List;

public class OntologyController {
    private OntModel model = null;


    /**
     * Inoitialize the model (ontology)
     */
    private void init() throws IOException {

        this.model = ModelFactory.createOntologyModel();
        //InputStream in = null;
        this.model.read(getClass().getClassLoader().getResourceAsStream("Security-NOQuery.owl"), "RDF/XML");
        //this.model.write(System.out);

    }

    /**
     * Execute query with one parameter(column)
     *
     * @param strQuery
     * @param column
     * @return result of the column (list of string)
     */
    private List<String> executeQuery(String strQuery, String column) {
        Query query = QueryFactory.create(strQuery);
        List<String> list = new JSONArray();
        ResultSet results;
        try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {

            results = qexec.execSelect();

            while (results.hasNext()) {

                //System.out.println(results.nextSolution().get("ad").toString().split("#")[1]);
                list.add(results.nextSolution().get(column).toString().split("#")[1]);


            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method to get all domains in the ontology
     *
     * @return List of the domains
     */
    public List<String> getAllDomains() {
        try {
            if (model == null)
                init();
            String getDomainsQuery =
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                            "PREFIX  : <http://www.semanticweb.org/user/ontologies/2021/7/security-criteria-ontology#>\n" +
                            "SELECT      ?ad\n" +
                            "WHERE       { ?ad rdf:type :ApplicationDomain }";

            return executeQuery(getDomainsQuery, "ad");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return list of all Security Criteria
     */
    public List<String> getAllCriteria() {
        try {
            if (model == null)
                init();
            String getCriteriaQuery =
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                            "PREFIX  : <http://www.semanticweb.org/user/ontologies/2021/7/security-criteria-ontology#>\n" +
                            "SELECT      ?sc\n" +
                            "WHERE       { ?sc rdf:type :SecurityCriterion }";
            return executeQuery(getCriteriaQuery, "sc");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return list of all security mechanisms
     */
    public List<String> getAllMechanisms() {
        try {
            if (model == null)
                init();
            String getCriteriaQuery =
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                            "PREFIX  : <http://www.semanticweb.org/user/ontologies/2021/7/security-criteria-ontology#>\n" +
                            "SELECT      ?sm\n" +
                            "WHERE       { ?sm rdf:type :SecurityMechanism }";
            return executeQuery(getCriteriaQuery, "sm");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param domain
     * @return the security mechanisms that belong to the given domain
     */
    public List<String> getMechanismsByDomain(String domain) {
        try {
            if (model == null)
                init();
            String getMechByDomQuery =
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                            "PREFIX  : <http://www.semanticweb.org/user/ontologies/2021/7/security-criteria-ontology#>\n" +
                            "SELECT      ?sm\n" +
                            "WHERE       { ?sm :belongsTo ?ad\n" +
                            "VALUES ?ad {\n" +
                            ":" + domain + " \n" +
                            "    } \n" +
                            "}";
            return executeQuery(getMechByDomQuery, "sm");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param domain
     * @return the security criteria that belong to a certain domain
     */
    public List<String> getCriteriaByDomain(String domain) {
        try {
            if (model == null)
                init();
            String getCriByDomQuery =
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                            "PREFIX  : <http://www.semanticweb.org/user/ontologies/2021/7/security-criteria-ontology#>\n" +
                            "SELECT  DISTINCT    ?sc\n" +
                            "WHERE       { ?sm :belongsTo ?ad\n" +
                            "VALUES ?ad {\n" +
                            ":" + domain + " \n" +
                            "    }\n" +
                            " . ?sm :enforces ?sc\n" +
                            "}";
            return executeQuery(getCriByDomQuery, "sc");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param domain
     * @param criteria
     * @return the security mechanisms of a given criteria in a given domain
     */
    public List<String> getCriteriaMechanismsByDomain(String domain, String criteria) {
        try {
            if (model == null)
                init();
            String getCriMechByDomQuery =
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                            "PREFIX  : <http://www.semanticweb.org/user/ontologies/2021/7/security-criteria-ontology#>\n" +
                            "SELECT  DISTINCT    ?sm\n" +
                            "WHERE       { ?sm :belongsTo ?ad\n" +
                            "VALUES ?ad {\n" +
                            ":" + domain + " \n" +
                            "    }\n" +
                            " . ?sm :enforces ?sc\n" +
                            "VALUES ?sc {\n" +
                            ":" + criteria + " \n" +
                            "    }\n" +
                            "}";
            return executeQuery(getCriMechByDomQuery, "sm");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get all mechanisms of a criterion
     * @param criteria
     * @return
     */
    public List<String> getCriteriaMechanisms(String criteria) {
        try {
            if (model == null)
                init();
            String getCriMechQuery =
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                            "PREFIX  : <http://www.semanticweb.org/user/ontologies/2021/7/security-criteria-ontology#>\n" +
                            "SELECT DISTINCT ?sm\n" +
                            "WHERE {?sm :enforces ?sc\n" +
                            "VALUES  ?sc{\n" +
                            "                            :"+criteria+"\n" +
                            "}\n" +
                            "}";
            return executeQuery(getCriMechQuery, "sm");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param domain
     * @param criteria
     * @param rel
     * @return The security criteria that have a given relationship (rel)
     * to a given criteria in a given domain
     */
    public List<String> getCriteriaByRelationByDomain(String domain, String criteria, String rel) {
        try {
            if (model == null)
                init();
            String getCriByRelByDomQuery =
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                            "PREFIX  : <http://www.semanticweb.org/user/ontologies/2021/7/security-criteria-ontology#>\n" +
                            "SELECT  DISTINCT    ?sc\n" +
                            "WHERE       { ?sm :belongsTo ?ad\n" +
                            "VALUES ?ad {\n" +
                            ":" + domain + " \n" +
                            "    }\n" +
                            " . ?sm :enforces ?sc\n" +
                            "VALUES ?sc1 {\n" +
                            ":" + criteria + " \n" +
                            "    }\n" +
                            ". ?sc ?relation ?sc1\n" +
                            "VALUES ?relation {\n" +
                            ":" + rel + " \n" +
                            "    }\n" +
                            "}";
            return executeQuery(getCriByRelByDomQuery, "sc");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param criteria
     * @param rel
     * @return The security criteria that have a given relationship (rel)
     * to a given criteria in a given domain
     */
    public List<String> getCriteriaByRelation(String rel, String criteria) {
        try {
            if (model == null)
                init();
            String getCriByRelQuery =
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                            "PREFIX  : <http://www.semanticweb.org/user/ontologies/2021/7/security-criteria-ontology#>\n" +
                            "SELECT  DISTINCT    ?sc\n" +
                            "WHERE       { " +
                            "VALUES ?sc1 {\n" +
                            ":" + criteria + " \n" +
                            "    }\n" +
                            ". ?sc ?relation ?sc1\n" +
                            "VALUES ?relation {\n" +
                            ":" + rel + " \n" +
                            "    }\n" +
                            "}";
            return executeQuery(getCriByRelQuery, "sc");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param domain
     * @param rel
     * @return the list of additional criteria that has relation to the criteria if the domain
     */
    public JSONObject getAdditionalCriteriaByRelationByDomain(String domain, String rel) {
        try {
            if (model == null)
                init();
            String getAddCriByRelQuery =
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                            "PREFIX  : <http://www.semanticweb.org/user/ontologies/2021/7/security-criteria-ontology#>\n" +
                            "SELECT  DISTINCT    ?sc  ?sc1\n" +
                            "WHERE       { ?sm :belongsTo ?ad\n" +
                            "VALUES ?ad {\n" +
                            "        :" + domain + " \n" +
                            "    }\n" +
                            " . ?sm :enforces ?sc1\n" +
                            ". ?sc ?relation ?sc1\n" +
                            "VALUES ?relation {\n" +
                            "        :" + rel + " \n" +
                            "    }\n" +
                            "}";
            Query query = QueryFactory.create(getAddCriByRelQuery);
            JSONObject jo = new JSONObject();
            List<String> list = new JSONArray();
            ResultSet results;
            try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {

                results = qexec.execSelect();
                JSONParser parser = new JSONParser();
                while (results.hasNext()) {

                    //System.out.println(results.nextSolution().get("ad").toString().split("#")[1]);
                    QuerySolution sln = results.nextSolution();
                    //System.out.println(sln);
                    //System.out.println(sln.get("sc").toString().split("#")[1]+" "+sln.get("sc1").toString().split("#")[1]);
                    if (jo.containsKey(sln.get("sc").toString().split("#")[1])) {

                        Object object = (Object) parser.parse(jo.get(sln.get("sc").toString().split("#")[1]).toString());
                        JSONArray l = (JSONArray) object;
                        l.add(sln.get("sc1").toString().split("#")[1]);
                        jo.put(sln.get("sc").toString().split("#")[1], l);
                    } else {
                        JSONArray j = new JSONArray();
                        j.add(sln.get("sc1").toString().split("#")[1]);
                        jo.put(sln.get("sc").toString().split("#")[1], j);
                    }


                }
                return jo;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Execute any query(select,describe,construct,ask)
     *
     * @param sQuery
     * @return
     */
    public JSONObject sparqlQuery(String sQuery) {
        try {
            if (model == null)
                init();
            Query query = QueryFactory.create("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                    "PREFIX  : <http://www.semanticweb.org/user/ontologies/2021/7/security-criteria-ontology#>\n "+
                    "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n " +  sQuery);
            JSONObject jo = new JSONObject();
            List<String> list1, list = new JSONArray();
            ResultSet results;
            // System.out.println(sQuery);
            try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {

                if (sQuery.contains("SELECT") || sQuery.contains("select")) {
                    results = qexec.execSelect();
                    JSONParser parser = new JSONParser();
                    while (results.hasNext()) {

                        //System.out.println(results.nextSolution().get("ad").toString().split("#")[1]);
                        QuerySolution sln = results.nextSolution();
                        //System.out.println(sln);
                        for (Iterator<String> it = sln.varNames(); it.hasNext(); ) {
                            String s = it.next();
                            if (jo.containsKey(s)) {
                                Object object = (Object) parser.parse(jo.get(s).toString());
                                JSONArray l = (JSONArray) object;
                                l.add(sln.get(s).toString().split("#")[1]);
                                jo.put(s, l);
                            } else {
                                JSONArray l = new JSONArray();
                                //System.out.println(sln.get(s).toString());
                                l.add(sln.get(s).toString().split("#")[1]);
                                jo.put(s, l);
                            }
                        }
                    }
                    return jo;
                } else if (sQuery.contains("ASK") || sQuery.contains("ask")) {
                    //System.out.println(sQuery);
                    Boolean ask = qexec.execAsk();
                    JSONObject object = new JSONObject();
                    object.put(sQuery, ask.toString());
                    return object;
                } else if (sQuery.contains("DESCRIBE") || sQuery.contains("describe")) {
                    Model m2 = qexec.execDescribe();
                    //JSONParser parser = new JSONParser();
                    System.out.println(m2.toString());
                    //Object object = (Object) parser.parse("{"+m2.toString()+"}");
                    jo.put("model", m2.toString());
                    return jo;

                } else if (sQuery.contains("CONSTRUCT") || sQuery.contains("construct")) {
                    Model m2 = qexec.execConstruct();
                    //JSONParser parser = new JSONParser();
                    System.out.println(m2.toString());
                    //Object object = (Object) parser.parse("{"+m2.toString()+"}");
                    jo.put("model", m2.toString());
                    return jo;

                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }

        return null;
    }

    /**
     * Execute update queries (Insert/delete)
     * @param sQuery
     * @return
     */

    public JSONObject updateQuery(String sQuery) {

        try {
            if (model == null)
                init();
            if (sQuery.contains("INSERT") || sQuery.contains("insert") || sQuery.contains("DELETE") || sQuery.contains("delete")) {
                //Model m2 = qexec.execConstruct();

                try {
                    //Dataset dataset = DatasetFactory.create();
                    //dataset.begin(ReadWrite.WRITE);
                    //dataset.addNamedModel("http://ontology/",model);

                    UpdateAction.parseExecute("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                            "PREFIX  : <http://www.semanticweb.org/user/ontologies/2021/7/security-criteria-ontology#>\n " +
                            "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n " + sQuery, model);

                    //dataset.commit();
                    //Model modelOut = dataset.getNamedModel("http://ontology/");
                    //System.out.println(modelOut.listObjects());
                    //System.out.println(this.getAllCriteria());
                    JSONObject object = new JSONObject();
                    object.put("insert", sQuery);
                    model.write(new FileWriter(new File("C:\\Users\\hnainihi\\Desktop\\AIDTools\\outputModel.owl")));
                    return object;
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
