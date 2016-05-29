package com.sia.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sia.ApplicationLogic.UserStateTrack;

@Path("/api/siaresponse")
public class SiaResponse {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getResponse(@QueryParam("isChatStart") boolean isChatStart, @QueryParam("input") String input) {
		return UserStateTrack.getMessage(isChatStart, input);
	}
}