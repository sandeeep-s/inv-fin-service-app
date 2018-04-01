package com.gefa.ekf.boundary.inbound.rest.v0_0_1.representations.converters;

import com.gefa.ekf.application.domain.IFAsset;
import com.gefa.ekf.boundary.inbound.rest.v0_0_1.representations.AssetRepresentation;
import com.gefa.ekf.boundary.inbound.rest.v0_0_1.representations.Link;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AssetConverter {

    public IFAsset toAsset(AssetRepresentation assetRepresentation) {

        return new IFAsset(assetRepresentation.getId(), assetRepresentation.getAssetName(), assetRepresentation.getManufacturerId(), assetRepresentation.getManufacturerName(), assetRepresentation.getObjectId(), assetRepresentation.getObjectName());

    }

    public IFAsset toAsset(AssetRepresentation assetRepresentation, Long assetId) {
        return new IFAsset(assetId, assetRepresentation.getAssetName(), assetRepresentation.getManufacturerId(), assetRepresentation.getManufacturerName(), assetRepresentation.getObjectId(), assetRepresentation.getObjectName());

    }

    public AssetRepresentation fromAsset(IFAsset IFAsset, Link... links) {
        return new AssetRepresentation(IFAsset, links);
    }

}
