package com.gefa.ekf.application.domain.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import com.gefa.ekf.application.domain.IFAsset;

@Singleton
public class MapAssetRepository implements AssetRepository {

	/*
	 * private static final MapAssetRepository thisRepository = new
	 * MapAssetRepository();
	 * 
	 * public static MapAssetRepository current() { return thisRepository; }
	 */
	private Map<Long, IFAsset> backingStore = new HashMap<Long, IFAsset>();

	public Long save(IFAsset IFAsset) {
		int size = backingStore.size();
		Long id = Long.valueOf(size + 1);
		backingStore.put(id, IFAsset);
		return id;
	}

	public Long update(IFAsset IFAsset, Long id) {
		backingStore.put(id, IFAsset);
		return id;
	}

	public IFAsset find(Long id) {
		return backingStore.get(id);
	}

	public IFAsset delete(Long id) {
		IFAsset IFAsset = backingStore.remove(id);
		return IFAsset;
	}

	@Override
	public Long saveAsset(IFAsset IFAsset) {
		int size = backingStore.size();
		Long id = Long.valueOf(size + 1);
		backingStore.put(id, IFAsset);
		return id;

	}

	@Override
	public void updateAsset(IFAsset IFAsset) {
		backingStore.put(IFAsset.getId(), IFAsset);
	}

	@Override
	public IFAsset getAsset(Long assetId) {
		return backingStore.get(assetId);
	}

	@Override
	public Boolean exists(Long assetNumber) {

		return null;
	}

	@Override
	public List<IFAsset> getAssetsByDealer(Long gevisNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
