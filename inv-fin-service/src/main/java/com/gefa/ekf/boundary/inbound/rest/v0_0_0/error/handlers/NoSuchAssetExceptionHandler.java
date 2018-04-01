package com.gefa.ekf.boundary.inbound.rest.v0_0_0.error.handlers;

import com.gefa.ekf.application.exceptions.NoSuchAssetException;
import com.gefa.ekf.boundary.inbound.rest.v0_0_0.error.ErrorCodeNum;
import com.gefa.ekf.boundary.inbound.rest.v0_0_0.error.GeneralErrorCode;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NoSuchAssetExceptionHandler implements ExceptionMapper<NoSuchAssetException> {

	@Override
	public Response toResponse(NoSuchAssetException exception) {
		return Response.status(Status.NOT_FOUND)
				.entity(new GeneralErrorCode(String.valueOf(Status.NOT_FOUND),
						ErrorCodeNum.RESOORCE_NOT_FOUND.getUserMessage(), exception.getLocalizedMessage().toString()))
				.build();
	}

}
