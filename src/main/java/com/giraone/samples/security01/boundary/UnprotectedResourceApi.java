package com.giraone.samples.security01.boundary;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/unprotected-api")
public class UnprotectedResourceApi extends Application
{	
	// Override using this code, when we have multiple base paths.
	// See http://www.adam-bien.com/roller/abien/entry/multiple_jax_rs_uris_in
	
	@Override
	public Set<Class<?>> getClasses()
	{
		Set<Class<?>> resources = new HashSet<>();
		resources.add(UnprotectedResourceEndpoint.class);
		return resources;
	}
}