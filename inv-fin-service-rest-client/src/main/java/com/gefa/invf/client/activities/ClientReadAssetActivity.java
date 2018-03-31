package com.gefa.invf.client.activities;

import com.gefa.invf.client.domain.Asset;
import com.gefa.invf.client.representations.AssetRepresentation;
import com.gefa.invf.client.representations.converters.AssetConverter;

import java.net.URI;

public class ClientReadAssetActivity extends Activity {

	private final URI assetURI;
	private com.gefa.invf.client.domain.Asset asset;
	private AssetConverter assetConverter =  new AssetConverter();

	public ClientReadAssetActivity(URI assetURI) {
		this.assetURI = assetURI;
	}

	public Asset readAsset() throws com.gefa.invf.client.exceptions.NotFoundException, com.gefa.invf.client.exceptions.ServiceFailureException {
			AssetRepresentation assetRepresentation = httpBinding.retrieveAsset(assetURI);
			Asset asset = assetConverter.toAsset(assetRepresentation);
			this.asset = asset;
			this.actions = new RepresentationHypermediaProcessor()
					.extractNextActionsFromAssetRepresentation(assetRepresentation);
			return asset;
	}
	
	public com.gefa.invf.client.domain.Asset getAsset() {
		return asset;
	}
}
