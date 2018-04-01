package com.gefa.ekf.boundary.inbound.rest.v0_0_1.representations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gefa.ekf.application.domain.IFAsset;

import java.util.Arrays;

public class AssetRepresentation extends Representation {

	private Long id;
	private String assetName;
	private Long manufacturerId;
	private String manufacturerName;
	private Long objectId;
	private String objectName;
	private String assetStatus;

	public AssetRepresentation() {

	}

	public AssetRepresentation(IFAsset IFAsset, Link... links) {
		this.assetName = IFAsset.getAssetName();
//		this.assetStatus = IFAsset.getAssetState().toString();
		this.manufacturerId = IFAsset.getManufacturerId();
		this.manufacturerName = IFAsset.getManufacturerName();
		this.objectName = IFAsset.getObjectName();
		this.objectId = IFAsset.getObjectId();
		this.links = Arrays.asList(links);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
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

	public String getAssetStatus() {
		return assetStatus;
	}

	public void setAssetStatus(String assetStatus) {
		this.assetStatus = assetStatus;
	}

	@JsonIgnore
	public Link getSelfLink() {
		return getLinkByName("self");
	}

	@JsonIgnore
	public Link getUpdateLink() {
		return getLinkByName("update");
	}

	@JsonIgnore
	public Link getRemoveLink() {
		return getLinkByName("remove");
	}

	@JsonIgnore
	public Link getApproveLink() {
		return getLinkByName("approve");
	}

}
