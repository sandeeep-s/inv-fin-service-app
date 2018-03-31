package com.gefa.ekf.application.domain.events;

import com.gefa.ekf.application.domain.IFAsset;

public class AssetCreatedEvent implements DomainEvent{

	private IFAsset IFAsset;

	public AssetCreatedEvent(){

    }

	public AssetCreatedEvent(IFAsset IFAsset){

	    this.IFAsset = IFAsset;
	}

    public IFAsset getIFAsset() {

	    return IFAsset;
    }
}
