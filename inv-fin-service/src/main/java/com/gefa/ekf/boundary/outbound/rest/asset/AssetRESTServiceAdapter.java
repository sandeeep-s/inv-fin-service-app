package com.gefa.ekf.boundary.outbound.rest.asset;

import com.gefa.ekf.application.domain.events.AssetCreatedEvent;
import com.gefa.ekf.client.activities.ClientCreateAssetActivity;
import com.gefa.ekf.client.activities.ClientReadAssetActivity;
import com.gefa.ekf.client.domain.Asset;
import io.reactivex.Observable;
import io.reactivex.Single;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@ApplicationScoped
public class AssetRESTServiceAdapter {

	@Inject
	private AssetRESTTranslater assetTranslater;

    private String getEntryPointURI() {
        return "http://localhost:8081/asset-service/asset";
    }

    public void createAsset(AssetCreatedEvent assetCreatedEvent) {

        try{
            ClientCreateAssetActivity clientCreateAssetActivity = new ClientCreateAssetActivity();

            clientCreateAssetActivity.createAsset(assetTranslater.toAsset(assetCreatedEvent), new URI(getEntryPointURI()));
        }catch(URISyntaxException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }

	}
    public List<Asset> getAssets(){
        return null;
    }

    public Observable<Asset> getAsset(Long assetId){
        Asset asset = null;
        try {
            URI assetURI = new URI(getEntryPointURI()+"/"+assetId);
            ClientReadAssetActivity clientReadAssetActivity = new ClientReadAssetActivity(assetURI);
            clientReadAssetActivity.readAsset();
            asset = clientReadAssetActivity.getAsset();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return Observable.just(asset);
    }

	public void updateAsset(AssetCreatedEvent assetCreatedEvent) {
	}

}
