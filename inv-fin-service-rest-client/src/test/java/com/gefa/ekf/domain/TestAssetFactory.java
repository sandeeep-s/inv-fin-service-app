package com.gefa.ekf.domain;

import com.gefa.invf.client.domain.Asset;

import java.util.concurrent.ThreadLocalRandom;

public class TestAssetFactory {

	public Asset createAsset() {
		int random = ThreadLocalRandom.current().nextInt(10000, 30000);

		String assetName = "JMSTEST-" + random;
		Long manufacturerId = 1L;
		Long objectId = 1L;

		return new Asset(assetName, manufacturerId, objectId);

	}

}
