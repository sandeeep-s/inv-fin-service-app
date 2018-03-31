package com.gefa.invf.client.activities;


public class RepresentationHypermediaProcessor {

	com.gefa.invf.client.activities.Actions extractNextActionsFromAssetRepresentation(com.gefa.invf.client.representations.AssetRepresentation assetRepresentation) {

		com.gefa.invf.client.activities.Actions actions = new com.gefa.invf.client.activities.Actions();

		if (null != assetRepresentation) {
			if (null != assetRepresentation.getSelfLink()) {
				actions.add(new com.gefa.invf.client.activities.ClientReadAssetActivity(assetRepresentation.getSelfLink().getUri()));
			}

			if (null != assetRepresentation.getUpdateLink()) {
				actions.add(new com.gefa.invf.client.activities.ClientUpdateAssetActivity(assetRepresentation.getUpdateLink().getUri()));
			}

			if (null != assetRepresentation.getRemoveLink()) {
				actions.add(new com.gefa.invf.client.activities.ClientRemoveAssetActivity(assetRepresentation.getRemoveLink().getUri()));
			}
		}

		return actions;

	}


}
