package com.gefa.ekf.application.domain.repositories;

import java.util.List;

import com.gefa.ekf.application.domain.IFAsset;

public interface AssetRepository {

	Long saveAsset(IFAsset IFAsset);

	void updateAsset(IFAsset IFAsset);

	IFAsset getAsset(Long assetId);

	Boolean exists(Long assetNumber);

	List<IFAsset> getAssetsByDealer(Long gevisNumber);
}
