package com.gefa.invf.client.activities;

import java.net.URI;

import com.gefa.invf.client.representations.converters.AssetConverter;

public class ClientUpdateAssetActivity extends com.gefa.invf.client.activities.Activity {

	private final URI assetURI;

	private com.gefa.invf.client.domain.Asset asset;

	private AssetConverter assetConverter;

	public ClientUpdateAssetActivity(URI assetURI) {
		this.assetURI = assetURI;
	}

	public void updateAsset(com.gefa.invf.client.domain.Asset asset) {
		try {
			com.gefa.invf.client.representations.AssetRepresentation assetRepresentation = httpBinding.updateAsset(assetConverter.fromAsset(asset), assetURI);
			this.asset = assetConverter.toAsset(assetRepresentation);
			this.actions = new RepresentationHypermediaProcessor()
					.extractNextActionsFromAssetRepresentation(assetRepresentation);
		} catch (com.gefa.invf.client.exceptions.MalformedAssetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (com.gefa.invf.client.exceptions.NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (com.gefa.invf.client.exceptions.CannotUpdateAssetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (com.gefa.invf.client.exceptions.ServiceFailureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public com.gefa.invf.client.domain.Asset getAsset() {
		return asset;
	}

}
