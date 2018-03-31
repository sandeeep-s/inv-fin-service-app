package com.gefa.ekf.boundary.outbound.rest.asset;

import com.gefa.ekf.application.domain.IFAsset;
import com.gefa.ekf.application.domain.events.AssetCreatedEvent;
import com.gefa.ekf.client.domain.Asset;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class AssetRESTService {

	@Inject
	private AssetRESTServiceAdapter assetServiceAdapter;

	public void createAsset(AssetCreatedEvent assetCreatedEvent) {

		assetServiceAdapter.createAsset(assetCreatedEvent);
	}

	public List<Asset> getAssets(){
		return assetServiceAdapter.getAssets();
	}

	public Asset getAsset(Long assetId){
		return assetServiceAdapter.getAsset(assetId);
	}

	public void updateAsset(AssetCreatedEvent assetCreatedEvent) {
		assetServiceAdapter.updateAsset(assetCreatedEvent);
	}

}
