package com.gefa.invf.client.representations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gefa.invf.client.domain.Asset;

/*JsonIgnoreProperties makes it a tolerant reader*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetRepresentation extends com.gefa.invf.client.representations.Representation {

	private Long id;
	private String assetName;
	private String assetStatus;
    private Long manufacturerId;
    private Long objectId;

	public AssetRepresentation() {

	}

	public AssetRepresentation(Asset asset) {
		this.assetName = asset.getAssetName();
		this.assetStatus = asset.getAssetStatus();
        this.manufacturerId = asset.getManufacturerId();
        this.objectId = asset.getObjectId();
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

    public Long getObjectId() {
        return objectId;
    }

    public String getAssetStatus() {
		return assetStatus;
	}

	public void setAssetStatus(String assetStatus) {
		this.assetStatus = assetStatus;
	}

	@JsonIgnore
	public com.gefa.invf.client.representations.Link getSelfLink() {
		return getLinkByName("self");
	}

	@JsonIgnore
	public com.gefa.invf.client.representations.Link getUpdateLink() {
		return getLinkByName("update");
	}

	@JsonIgnore
	public com.gefa.invf.client.representations.Link getRemoveLink() {
		return getLinkByName("remove");
	}

}
