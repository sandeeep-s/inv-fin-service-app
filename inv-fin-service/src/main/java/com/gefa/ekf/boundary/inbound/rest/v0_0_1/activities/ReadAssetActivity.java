package com.gefa.ekf.boundary.inbound.rest.v0_0_1.activities;

import com.gefa.ekf.application.AssetService;
import com.gefa.ekf.application.domain.AssetCreatedState;
import com.gefa.ekf.application.domain.IFAsset;
import com.gefa.ekf.application.exceptions.NoSuchAssetException;
import com.gefa.ekf.boundary.inbound.rest.v0_0_1.representations.AssetRepresentation;
import com.gefa.ekf.boundary.inbound.rest.v0_0_1.representations.Link;
import com.gefa.ekf.boundary.inbound.rest.v0_0_1.representations.converters.AssetConverter;
import io.reactivex.Observable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@ApplicationScoped
public class ReadAssetActivity {

	@Inject
	private AssetService assetService;

	@Inject
	private AssetConverter assetConverter;

	public ReadAssetActivity() {
		// TODO Auto-generated constructor stub
	}

	public Observable<AssetRepresentation> retrieveById(Long assetId, UriInfo uriInfo) throws NoSuchAssetException {
		Observable<IFAsset> ifAssetO = assetService.getAsset(assetId);

		return ifAssetO.map(ifAsset -> {
			if (null == ifAsset) {
				throw new NoSuchAssetException("IFAsset with id " + assetId + " not found");
			}

			String assetURI = uriInfo.getRequestUri().toString();

			Link assetSelflink = new Link("self", assetURI, MediaType.APPLICATION_XML);
			AssetRepresentation assetRepresentation = new AssetRepresentation(ifAsset, assetSelflink);

			if (new AssetCreatedState(ifAsset).equals(ifAsset.getAssetState())) {
				String approveURI = uriInfo.getBaseUri() + "approve/" + assetId;
				Link assetApprovelink = new Link("approve", approveURI, MediaType.APPLICATION_XML);
				Link assetUpdatelink = new Link("update", assetURI, MediaType.APPLICATION_XML);
				Link assetDeletelink = new Link("remove", assetURI, MediaType.APPLICATION_XML);
				assetRepresentation = new AssetRepresentation(ifAsset, assetSelflink, assetUpdatelink, assetDeletelink,
						assetApprovelink);
			}
			return assetRepresentation;
		});


	}

}
