package com.gefa.ekf.boundary.outbound.async.fit;

import com.gefa.ekf.application.domain.events.AssetCreatedEvent;
import com.gefa.ekf.boundary.outbound.async.fit.events.AssetEvent;

public class AssetEventTranslater {

	public AssetEvent toAssetEvent(AssetCreatedEvent assetCreatedEvent) {


		return new AssetEvent("asset-created", assetCreatedEvent.getIFAsset().getAssetName(),
				assetCreatedEvent.getIFAsset().getManufacturerId(),
				assetCreatedEvent.getIFAsset().getManufacturerName(),
				assetCreatedEvent.getIFAsset().getObjectId(),
				assetCreatedEvent.getIFAsset().getObjectName());
	}

}
