package com.gefa.ekf.boundary.outbound.rest.object;


import com.gefa.objekt.client.activities.ClientReadObjektActivity;
import com.gefa.objekt.client.domain.Objekt;

import javax.enterprise.context.ApplicationScoped;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@ApplicationScoped
public class ObjektRESTServiceAdapter {

    private String getEntryPointURI() {
        return "http://localhost:8085/objekt-service/objekt";
    }

    public List<Objekt> getObjekts(){
        return null;
    }

    public Objekt getObjekt(Long objectId){
        Objekt object = null;
        try {
            URI objectURI = new URI(getEntryPointURI()+"/"+objectId);
            ClientReadObjektActivity clientReadObjektActivity = new ClientReadObjektActivity(objectURI);
            clientReadObjektActivity.readObjekt();
            object = clientReadObjektActivity.getObjekt();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return object;
    }

}
