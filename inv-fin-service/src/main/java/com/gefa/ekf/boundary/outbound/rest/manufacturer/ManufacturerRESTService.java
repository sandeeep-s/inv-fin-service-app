package com.gefa.ekf.boundary.outbound.rest.manufacturer;


import com.gefa.manufacturer.client.domain.Manufacturer;
import io.reactivex.Observable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ManufacturerRESTService {

	@Inject
	private ManufacturerRESTServiceAdapter manufacturerServiceAdapter;

	public List<Manufacturer> getManufacturers(){
		return manufacturerServiceAdapter.getManufacturers();
	}

	public Observable<Manufacturer> getManufacturer(Long manufacturerId){
		return manufacturerServiceAdapter.getManufacturer(manufacturerId);
	}

}
