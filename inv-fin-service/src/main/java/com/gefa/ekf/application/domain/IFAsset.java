package com.gefa.ekf.application.domain;

public class IFAsset extends AggregateRoot {

    private Long id;
    private String assetName;
    private Long manufacturerId;
    private String manufacturerName;
    private Long objectId;
    private String objectName;

    private AssetState assetState;

    public IFAsset(String assetName, Long manufacturerId, String manufacturerName, Long objectId, String objectName) {
        this.assetName = assetName;
        this.manufacturerId = manufacturerId;
        this.manufacturerName = manufacturerName;
        this.objectId = objectId;
        this.objectName = objectName;
    }

    public IFAsset(Long id, String assetName, Long manufacturerId, String manufacturerName, Long objectId, String objectName) {
        this.id = id;
        this.assetName = assetName;
        this.manufacturerId = manufacturerId;
        this.manufacturerName = manufacturerName;
        this.objectId = objectId;
        this.objectName = objectName;
    }

    public Long getId() {
        return id;
    }

    public String getAssetName() {
        return assetName;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public Long getObjectId() {
        return objectId;
    }

    public String getObjectName() {
        return objectName;
    }

    public AssetState getAssetState() {
        return assetState;
    }

    public void setAssetState(AssetState assetState) {
        this.assetState = assetState;
    }

    public void moveToCreatedState() {
        assetState.moveToCreatedState();
    }

    public void moveToDeletedState() {
        assetState.moveToDeletedState();
    }

}
