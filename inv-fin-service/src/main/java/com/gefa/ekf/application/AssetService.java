package com.gefa.ekf.application;

import com.gefa.ekf.application.domain.AssetCreatedState;
import com.gefa.ekf.application.domain.IFAsset;
import com.gefa.ekf.application.domain.events.AssetCreatedEvent;
import com.gefa.ekf.application.domain.events.DomainEvent;
import com.gefa.ekf.application.domain.repositories.MapAssetRepository;
import com.gefa.ekf.application.exceptions.AssetCreationException;
import com.gefa.ekf.application.exceptions.AssetRemovalException;
import com.gefa.ekf.application.exceptions.AssetUpdateException;
import com.gefa.ekf.application.infrastructure.RXDomainEventsDispatcher;
import com.gefa.ekf.boundary.outbound.rest.asset.AssetRESTService;
import com.gefa.ekf.boundary.outbound.rest.manufacturer.ManufacturerRESTService;
import com.gefa.ekf.boundary.outbound.rest.object.ObjektRESTService;
import com.gefa.ekf.client.domain.Asset;
import com.gefa.manufacturer.client.domain.Manufacturer;
import com.gefa.objekt.client.domain.Objekt;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AssetService {

    @Inject
    private MapAssetRepository assetRepository;
    // private AssetRepository assetRepository;

    @Inject
    private AssetRESTService assetRESTService;

    @Inject
    private ManufacturerRESTService manufacturerRESTService;

    @Inject
    private ObjektRESTService objektRESTService;

    @Inject
    private RXDomainEventsDispatcher domainEventsDispatcher;

    public Long create(IFAsset IFAsset) {

        Long assetId;
        try {
            IFAsset.setAssetState(new AssetCreatedState(IFAsset));
            assetId = assetRepository.saveAsset(IFAsset);
            IFAsset.addDomainEvent(new AssetCreatedEvent(IFAsset));
            for (DomainEvent domainEvent : IFAsset.getDomainEvents()) {
                domainEventsDispatcher.dispatch(domainEvent);
            }
        } catch (Exception e) {
            throw new AssetCreationException();
        }

        return assetId;
    }

    public void update(IFAsset IFAsset) {

        try {
            IFAsset IFAssetFromRepo = assetRepository.getAsset(IFAsset.getId());
            IFAssetFromRepo.moveToCreatedState();
            assetRepository.updateAsset(IFAsset);

        } catch (UnsupportedOperationException e) {
            throw new AssetUpdateException();
        } catch (Exception e) {
            throw new AssetUpdateException();
        }

        // TODO Update the assetFromRepo details onto IFAsset
    }

    public IFAsset getAsset(Long assetId) {

        Asset asset = assetRESTService.getAsset(assetId);
        Long manufacturerId = asset.getManufacturerId();
        Manufacturer manufacturer = manufacturerRESTService.getManufacturer(manufacturerId);
        Objekt object = objektRESTService.getObjekt(asset.getObjectId());


        return new IFAsset(asset.getId(), asset.getAssetName(), asset.getManufacturerId(), manufacturer.getManufacturerName(), asset.getObjectId(), object.getObjektName());
    }

    public IFAsset removeAsset(Long assetId) {
        try {
            IFAsset IFAsset = assetRepository.getAsset(assetId);
            IFAsset.moveToDeletedState();
            return assetRepository.delete(assetId);
        } catch (UnsupportedOperationException e) {
            throw new AssetRemovalException();
        }
    }

}
