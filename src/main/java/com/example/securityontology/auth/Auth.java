package com.example.securityontology.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.securityontology.api.Main;
import com.example.securityontology.engine.Engine;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.*;
import org.semanticweb.owlapi.annotations.HasPriority;


@Main.Secured
@HasPriority(Priorities.AUTHENTICATION)
@Provider
public class Auth implements ContainerRequestFilter, ContainerResponseFilter {
    Engine engine=new Engine();

    public void verifyToken(String token) {
        try {

            //Check the revoked tokens
            /*BufferedReader bufReader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("revoked")));
            String line = bufReader.readLine();
            while (line != null) {
                if (line.equals(token)) {
                    throw new NotAuthorizedException("Token Revoked");
                }
                line = bufReader.readLine();
            }
            bufReader.close();
            */
            //Set the algorithm used to verify the token
            Algorithm algorithm = Algorithm.HMAC256(this.engine.getApiSecret());

            //Set the issuer to be verified in the token
            JWTVerifier verifier =
                    JWT.require(algorithm).withIssuer(engine.getISSUER()).build();
            DecodedJWT jwt = verifier.verify(token);

            String user = jwt.getSubject();

            if (user == null) {
                throw new NotAuthorizedException("User not found");
            }

        } catch (JWTVerificationException ex) {
            throw new NotAuthorizedException("Invalid token", ex);
        } /* catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/ catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * A preflight request is an OPTIONS request
     * with an Origin header.
     */
    private static boolean isPreflightRequest(ContainerRequestContext request) {
        return request.getHeaderString("Origin") != null
                && request.getMethod().equalsIgnoreCase("OPTIONS");
    }


    /**
     * Filter all incoming requests (that have the @SECURED tag) and check token
     * @param containerRequestContext
     *
     */


    @Override
    public void filter(ContainerRequestContext containerRequestContext)  {
        if (isPreflightRequest(containerRequestContext)) {
            containerRequestContext.abortWith(Response.ok().build());
            return;
        }
        // Get the HTTP Authorization header from the request
        String authorizationHeader =
                containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Check if the HTTP Authorization header is present and formatted correctly
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        // Extract the token from the HTTP Authorization header
        String token = authorizationHeader.substring("Bearer".length()).trim();

        try {

            // Verify the token
            verifyToken(token);

        } catch (Exception e) {
            containerRequestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
/*        // Get the HTTP Authorization header from the request
        String authorizationHeader =
                requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Check if the HTTP Authorization header is present and formatted correctly
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        // Extract the token from the HTTP Authorization header
        String token = authorizationHeader.substring("Bearer".length()).trim();

        try {

            // Verify the token
            verifyToken(token);

        } catch (Exception e) {
           requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED).build());
        }*/
        responseContext.getHeaders().add(
                "Access-Control-Allow-Origin", "*");
        if (isPreflightRequest(requestContext)) {
            responseContext.getHeaders().add(
                    "Access-Control-Allow-Credentials", "true");
            responseContext.getHeaders().add(
                    "Access-Control-Allow-Headers",
                    "origin, content-type, accept, authorization");
            responseContext.getHeaders().add(
                    "Access-Control-Allow-Methods",
                    "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        }
    }
}
