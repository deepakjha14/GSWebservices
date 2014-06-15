/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dimension.webservices;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import jersey.repackaged.com.google.common.collect.Multimap;

/**
 *
 * @author Deepak
 */
@PreMatching
public class GSRequestFilter implements ContainerRequestFilter{
     @Override
    public void filter(ContainerRequestContext requestContext)
                    throws IOException {
        final SecurityContext securityContext = requestContext.getSecurityContext();
        MultivaluedMap<String, String> requestHeader = requestContext.getHeaders();
        /*if (securityContext == null || !securityContext.isUserInRole("privileged")) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).
                        entity("User cannot access the resource.").build());
        }*/
    }
}
