package com.gefa.ekf.boundary.outbound.async.fit;

import com.gefa.ekf.application.domain.events.AssetCreatedEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AssetEventMessagingAdapter {

	@Inject
	private AssetEventTranslater assetEventTranslater;

	@Inject
	private JMSFacade jmsBinding;

	public void createAsset(AssetCreatedEvent assetCreatedEvent) {
		jmsBinding.createAsset(assetEventTranslater.toAssetEvent(assetCreatedEvent));
	}

	public void updateAsset(AssetCreatedEvent assetCreatedEvent) {
		jmsBinding.updateAsset(assetEventTranslater.toAssetEvent(assetCreatedEvent));
	}

}
