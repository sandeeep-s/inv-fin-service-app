package com.gefa.ekf.boundary.outbound.rest.asset;

import com.gefa.ekf.application.domain.events.AssetCreatedEvent;
import com.gefa.ekf.application.domain.events.DomainEvent;
import com.gefa.ekf.application.domain.events.handlers.DomainEventHandler;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AssetCreatedEventRESTHandler implements DomainEventHandler {

	@Inject
	private AssetRESTService assetRestService;

	@Override
	public void handle(DomainEvent assetCreatedEvent) {

		assetRestService.createAsset((AssetCreatedEvent)assetCreatedEvent);
	}

}
