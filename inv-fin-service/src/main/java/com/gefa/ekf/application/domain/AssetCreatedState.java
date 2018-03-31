package com.gefa.ekf.application.domain;

public class AssetCreatedState extends AssetState {

    private IFAsset IFAsset;

    public AssetCreatedState(IFAsset IFAsset) {

        this.IFAsset = IFAsset;
    }

    @Override
    public void moveToDeletedState() {

        IFAsset.setAssetState(new AssetDeletedState(IFAsset));
    }

}
