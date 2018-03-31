package com.gefa.ekf.boundary.outbound.async.fit;

import com.gefa.ekf.application.domain.events.AssetCreatedEvent;
import com.gefa.ekf.application.domain.events.DomainEvent;
import com.gefa.ekf.application.domain.events.handlers.DomainEventHandler;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AssetCreatedEventHandler implements DomainEventHandler {

	@Inject
	private AssetEventMessagingAdapter assetEventMessagingAdapter;

	@Override
	public void handle(DomainEvent assetCreatedEvent) {

		assetEventMessagingAdapter.createAsset((AssetCreatedEvent)assetCreatedEvent);
	}

}
