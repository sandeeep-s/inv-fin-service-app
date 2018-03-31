package com.gefa.ekf.boundary.outbound.rest.object;


import com.gefa.objekt.client.domain.Objekt;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ObjektRESTService {

	@Inject
	private ObjektRESTServiceAdapter objectServiceAdapter;

	public List<Objekt> getObjekts(){
		return objectServiceAdapter.getObjekts();
	}

	public Objekt getObjekt(Long objectId){
		return objectServiceAdapter.getObjekt(objectId);
	}

}
