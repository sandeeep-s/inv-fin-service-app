package com.gefa.invf.client.representations.converters;


import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AssetConverter {

    public com.gefa.invf.client.domain.Asset toAsset(com.gefa.invf.client.representations.AssetRepresentation assetRepresentation) {

        return new com.gefa.invf.client.domain.Asset(assetRepresentation.getId(), assetRepresentation.getAssetName(), assetRepresentation.getManufacturerId(), assetRepresentation.getObjectId());

    }

    public com.gefa.invf.client.domain.Asset toAsset(com.gefa.invf.client.representations.AssetRepresentation assetRepresentation, Long assetId) {
        return new com.gefa.invf.client.domain.Asset(assetId, assetRepresentation.getAssetName(), assetRepresentation.getManufacturerId(), assetRepresentation.getObjectId());

    }

    public com.gefa.invf.client.representations.AssetRepresentation fromAsset(com.gefa.invf.client.domain.Asset asset) {

        return new com.gefa.invf.client.representations.AssetRepresentation(asset);
    }

}
