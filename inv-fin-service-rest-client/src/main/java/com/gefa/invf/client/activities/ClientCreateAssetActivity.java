package com.gefa.invf.client.activities;

import com.gefa.invf.client.representations.converters.AssetConverter;

import java.net.URI;

public class ClientCreateAssetActivity extends com.gefa.invf.client.activities.Activity {

	private com.gefa.invf.client.domain.Asset asset;

	private AssetConverter assetConverter = new AssetConverter();

	public void createAsset(com.gefa.invf.client.domain.Asset asset, URI assetURI) {

		try {
			com.gefa.invf.client.representations.AssetRepresentation assetRepresentation = httpBinding.createAsset(assetConverter.fromAsset(asset), assetURI);
			this.asset = assetConverter.toAsset(assetRepresentation);
			this.actions = new RepresentationHypermediaProcessor()
					.extractNextActionsFromAssetRepresentation(assetRepresentation);

		} catch (com.gefa.invf.client.exceptions.MalformedAssetException e) {
			e.printStackTrace();
		} catch (com.gefa.invf.client.exceptions.ServiceFailureException e) {
			e.printStackTrace();
		}

	}

	public com.gefa.invf.client.domain.Asset getAsset() {
		return asset;
	}

}
