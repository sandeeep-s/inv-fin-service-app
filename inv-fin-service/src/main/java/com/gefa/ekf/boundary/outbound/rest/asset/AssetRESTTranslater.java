package com.gefa.ekf.boundary.outbound.rest.asset;

import com.gefa.ekf.application.domain.events.AssetCreatedEvent;
import com.gefa.ekf.client.domain.Asset;

public class AssetRESTTranslater {

	public Asset toAsset(AssetCreatedEvent assetCreatedEvent) {


	    Asset asset = new Asset(assetCreatedEvent.getIFAsset().getAssetName(),
				assetCreatedEvent.getIFAsset().getManufacturerId(),
				assetCreatedEvent.getIFAsset().getObjectId());
		return asset;
	}

}
