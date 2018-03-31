package com.gefa.invf.client.activities;

import java.net.URI;

import com.gefa.invf.client.representations.converters.AssetConverter;

public class ClientRemoveAssetActivity extends com.gefa.invf.client.activities.Activity {

    private final URI assetURI;

    private com.gefa.invf.client.domain.Asset asset;

    private AssetConverter assetConverter;

    public ClientRemoveAssetActivity(URI assetURI) {
        this.assetURI = assetURI;
    }

    public void removeAsset(com.gefa.invf.client.domain.Asset asset) {
        try {
            com.gefa.invf.client.representations.AssetRepresentation assetRepresentation = httpBinding.removeAsset(assetURI);
            this.asset = assetConverter.toAsset(assetRepresentation);
        } catch (com.gefa.invf.client.exceptions.NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (com.gefa.invf.client.exceptions.ServiceFailureException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (com.gefa.invf.client.exceptions.CannotCancelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public com.gefa.invf.client.domain.Asset getAsset() {
        return asset;
    }

}
