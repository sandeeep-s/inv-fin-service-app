package com.gefa.ekf.boundary.outbound.async.fit.events;

import com.gefa.ekf.boundary.AbstractTransferObject;

public class AssetEvent extends AbstractTransferObject {

    private String eventType;
	private String assetName;
    private Long manufacturerId;
    private String manufacturerName;
    private Long objectId;
    private String objectName;

    public AssetEvent(String eventType, String assetName, Long manufacturerId, String manufacturerName, Long objectId, String objectName) {
        this.eventType = eventType;
        this.assetName = assetName;
        this.manufacturerId = manufacturerId;
        this.manufacturerName = manufacturerName;
        this.objectId = objectId;
        this.objectName = objectName;
    }

    public String getEventType() {
        return eventType;
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
}