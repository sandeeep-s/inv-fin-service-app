package com.gefa.invf.client.network;

import com.gefa.invf.client.exceptions.CannotCancelException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

public class HttpBinding {

    public com.gefa.invf.client.representations.AssetRepresentation createAsset(com.gefa.invf.client.representations.AssetRepresentation assetRepresentation, URI assetURI)
            throws com.gefa.invf.client.exceptions.MalformedAssetException, com.gefa.invf.client.exceptions.ServiceFailureException {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(assetURI);

        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(assetRepresentation, MediaType.APPLICATION_JSON));

        int status = response.getStatus();

        if (status == 400) {
            throw new com.gefa.invf.client.exceptions.MalformedAssetException();
        } else if (status == 500) {
            throw new com.gefa.invf.client.exceptions.ServiceFailureException();
        } else if (status == 201) {
            return response.readEntity(com.gefa.invf.client.representations.AssetRepresentation.class);
        }

        throw new RuntimeException(String.format("Unexpected response [%d] while creating asset resource [%s]", status,
                assetURI.toString()));

    }

    public com.gefa.invf.client.representations.AssetRepresentation retrieveAsset(URI assetURI) throws com.gefa.invf.client.exceptions.NotFoundException, com.gefa.invf.client.exceptions.ServiceFailureException {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(assetURI);

        Response response = target.request(MediaType.APPLICATION_JSON).get();

        int status = response.getStatus();

        if (status == 404) {
            throw new com.gefa.invf.client.exceptions.NotFoundException();
        } else if (status == 500) {
            throw new com.gefa.invf.client.exceptions.ServiceFailureException();
        } else if (status == 200) {
            return response.readEntity(com.gefa.invf.client.representations.AssetRepresentation.class);
        }

        throw new RuntimeException(String.format("Unexpected response [%d] while retrieveing asset resource [%s]",
                status, assetURI.toString()));
    }

    public com.gefa.invf.client.representations.AssetRepresentation updateAsset(com.gefa.invf.client.representations.AssetRepresentation assetRepresentation, URI assetURI)
            throws com.gefa.invf.client.exceptions.MalformedAssetException, com.gefa.invf.client.exceptions.NotFoundException, com.gefa.invf.client.exceptions.CannotUpdateAssetException, com.gefa.invf.client.exceptions.ServiceFailureException {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(assetURI);

        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(assetRepresentation, MediaType.APPLICATION_JSON));

        int status = response.getStatus();

        if (400 == status) {
            throw new com.gefa.invf.client.exceptions.MalformedAssetException();
        } else if (404 == status) {
            throw new com.gefa.invf.client.exceptions.NotFoundException();
        } else if (409 == status) {
            throw new com.gefa.invf.client.exceptions.CannotUpdateAssetException();
        } else if (500 == status) {
            throw new com.gefa.invf.client.exceptions.ServiceFailureException();
        } else if (200 == status) {
            return response.readEntity(com.gefa.invf.client.representations.AssetRepresentation.class);
        }

        throw new RuntimeException(String.format("Unexpected response [%d] while updating asset resource [%s]", status,
                assetURI.toString()));
    }

    public com.gefa.invf.client.representations.AssetRepresentation removeAsset(URI assetURI)
            throws com.gefa.invf.client.exceptions.NotFoundException, com.gefa.invf.client.exceptions.ServiceFailureException, com.gefa.invf.client.exceptions.CannotCancelException {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(assetURI);

        Response response = target.request(MediaType.APPLICATION_JSON).delete();

        int status = response.getStatus();

        if (status == 404) {
            throw new com.gefa.invf.client.exceptions.NotFoundException();
        } else if (status == 405) {
            throw new CannotCancelException();
        } else if (status == 500) {
            throw new com.gefa.invf.client.exceptions.ServiceFailureException();
        } else if (status == 200) {
            return response.readEntity(com.gefa.invf.client.representations.AssetRepresentation.class);
        }

        throw new RuntimeException(String.format("Unexpected response [%d] while removing asset resource [%s]", status,
                assetURI.toString()));
    }

}
