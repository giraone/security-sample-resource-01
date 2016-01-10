package com.giraone.samples.security01.boundary;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
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
@DeclareRoles({"AdministratorRole", "UserRole", "GuestRole"})
@DenyAll
public class ProtectedResourceEndpoint extends BaseEndpoint
{	
    @GET
    @Path("/version")
    @Produces("application/json; charset=UTF-8")
    @PermitAll
    public String version()
    {
    	return "1.0";
    }

    @GET
    @Path("/user")
    @Produces("application/json; charset=UTF-8")
    @RolesAllowed({ "AdministratorRole", "UserRole" })
    public UserInfo user(@Context HttpServletRequest request)
    {
    	UserInfo ret = new UserInfo(request);
    	return ret;
    }
    
    @GET
    @Path("/admin")
    @Produces("application/json; charset=UTF-8")
    @RolesAllowed({ "AdministratorRole" })
    public String adminInfo()
    {
    	return "Only-for-Admins";
    }
}