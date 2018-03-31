package com.gefa.ekf.application.domain;

public class AssetDeletedState extends AssetState {

	private IFAsset IFAsset;

	public AssetDeletedState(IFAsset IFAsset) {
		this.IFAsset = IFAsset;
	}

}
