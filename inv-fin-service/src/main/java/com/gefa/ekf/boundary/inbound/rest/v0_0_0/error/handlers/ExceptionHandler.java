package com.gefa.ekf.boundary.inbound.rest.v0_0_0.error.handlers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

//@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		return Response.serverError().build();
	}

}
