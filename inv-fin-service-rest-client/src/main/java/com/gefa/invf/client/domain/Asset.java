package com.gefa.invf.client.domain;

public class Asset {

    private Long id;
    private String assetName;
    private String assetStatus;
    private Long manufacturerId;
    private Long objectId;

    public Asset() {

    }

    public Asset(String assetName, Long manufacturerId, Long objectId) {
        super();
        this.assetName = assetName;
        this.manufacturerId = manufacturerId;
        this.objectId = objectId;
    }

    public Asset(Long id, String assetName, Long manufacturerId, Long objectId) {
        super();
        this.id = id;
        this.assetName = assetName;
        this.manufacturerId = manufacturerId;
        this.objectId = objectId;
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

    public Long getObjectId() {
        return objectId;
    }

    public String getAssetStatus() {
        return assetStatus;
    }
}
