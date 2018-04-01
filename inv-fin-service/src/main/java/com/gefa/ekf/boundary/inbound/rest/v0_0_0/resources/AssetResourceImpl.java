package com.gefa.ekf.boundary.inbound.rest.v0_0_0.resources;

import com.gefa.ekf.application.exceptions.AssetRemovalException;
import com.gefa.ekf.boundary.inbound.rest.v0_0_0.activities.CreateAssetActivity;
import com.gefa.ekf.boundary.inbound.rest.v0_0_0.activities.ReadAssetActivity;
import com.gefa.ekf.boundary.inbound.rest.v0_0_0.activities.RemoveAssetActivity;
import com.gefa.ekf.boundary.inbound.rest.v0_0_0.activities.UpdateAssetActivity;
import com.gefa.ekf.boundary.inbound.rest.v0_0_0.representations.AssetRepresentation;
import io.reactivex.Observable;

import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import java.util.concurrent.atomic.AtomicReference;

public class AssetResourceImpl implements AssetResource {

    @Inject
    private CreateAssetActivity createAssetActivity;

    @Inject
    private UpdateAssetActivity updateAssetActivity;

    @Inject
    private ReadAssetActivity readAssetActivity;

    @Inject
    private RemoveAssetActivity removeAssetActivity;

    @Context
    UriInfo uriInfo;

    public Response addAsset(AssetRepresentation assetRepresentation) {
        ResponseBuilder builder = null;
        try {
            AssetRepresentation responseRepresentation = createAssetActivity.create(assetRepresentation, uriInfo);
            builder = Response.status(Status.CREATED).type(MediaType.APPLICATION_JSON)
                    .entity(responseRepresentation);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return builder.build();
    }

    public AssetRepresentation updateAsset(Long assetId, AssetRepresentation assetRepresentation) {
        AssetRepresentation responseRepresentation = updateAssetActivity.update(assetId, assetRepresentation, uriInfo);
        return responseRepresentation;
    }

    public Response getAsset(Long assetId) {
        final AtomicReference<Response> responseContainer = new AtomicReference<>();
        try {
            Observable<AssetRepresentation> responseRepresentation = readAssetActivity.retrieveById(assetId, uriInfo);
            responseRepresentation.subscribe(assetRepresentation -> {
                responseContainer.set(Response.status(Status.OK).type(MediaType.APPLICATION_JSON)
                        .entity(assetRepresentation).build());
            }, Throwable::printStackTrace);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Response response = responseContainer.get();
        return response;
    }

    public Response removeAsset(Long assetId) {
        try {
            AssetRepresentation responseRepresentation = removeAssetActivity.delete(assetId);
            ResponseBuilder builder = Response.status(Status.OK).type(MediaType.APPLICATION_JSON)
                    .entity(responseRepresentation);
            return builder.build();
        } catch (AssetRemovalException e) {
            return Response.status(Status.METHOD_NOT_ALLOWED).build();
        }
    }

}
