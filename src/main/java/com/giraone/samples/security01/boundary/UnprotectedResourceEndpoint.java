package com.giraone.samples.security01.boundary;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import com.giraone.samples.common.UserInfo;
import com.giraone.samples.common.boundary.BaseEndpoint;

/**
 * Resource samples end point for unprotected resources.
 */
@Stateless
@Path("/info")
@PermitAll
public class UnprotectedResourceEndpoint extends BaseEndpoint
{	
    @GET
    @Path("/version")
    @Produces("application/json; charset=UTF-8")
    public String version()
    {
    	return "1.0";
    }

    @GET
    @Path("/user")
    @Produces("application/json; charset=UTF-8")
    public UserInfo user(@Context HttpServletRequest request)
    {
    	UserInfo ret = new UserInfo(request);
    	return ret;
    }
}