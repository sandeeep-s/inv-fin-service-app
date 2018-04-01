package com.gefa.ekf.boundary.inbound.rest.v0_0_1.activities;

import com.gefa.ekf.application.AssetService;
import com.gefa.ekf.application.domain.IFAsset;
import com.gefa.ekf.boundary.inbound.rest.v0_0_1.representations.AssetRepresentation;
import com.gefa.ekf.boundary.inbound.rest.v0_0_1.representations.Link;
import com.gefa.ekf.boundary.inbound.rest.v0_0_1.representations.converters.AssetConverter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@ApplicationScoped
public class CreateAssetActivity {

	@Inject
	private AssetService assetService;

	@Inject
	private AssetConverter assetConverter;

	public CreateAssetActivity() {

	}

	public AssetRepresentation create(AssetRepresentation assetRepresentation, UriInfo uriInfo) {

		IFAsset IFAsset = assetConverter.toAsset(assetRepresentation);
		Long assetId = assetService.create(IFAsset);

		String assetURI = uriInfo.getBaseUri() + "IFAsset/" + assetId;
		Link assetSelflink = new Link("self", assetURI, MediaType.APPLICATION_XML);
		Link assetUpdatelink = new Link("update", assetURI, MediaType.APPLICATION_XML);
		Link assetDeletelink = new Link("remove", assetURI, MediaType.APPLICATION_XML);

		return new AssetRepresentation(IFAsset, assetSelflink, assetUpdatelink, assetDeletelink);
	}

}
