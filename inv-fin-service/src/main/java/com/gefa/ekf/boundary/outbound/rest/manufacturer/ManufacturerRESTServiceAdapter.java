package com.gefa.ekf.boundary.outbound.rest.manufacturer;


import com.gefa.manufacturer.client.activities.ClientReadManufacturerActivity;
import com.gefa.manufacturer.client.domain.Manufacturer;

import javax.enterprise.context.ApplicationScoped;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@ApplicationScoped
public class ManufacturerRESTServiceAdapter {

    private String getEntryPointURI() {
        return "http://localhost:8084/manufacturer-service/manufacturer";
    }

    public List<Manufacturer> getManufacturers(){
        return null;
    }

    public Manufacturer getManufacturer(Long manufacturerId){
        Manufacturer manufacturer = null;
        try {
            URI manufacturerURI = new URI(getEntryPointURI()+"/"+manufacturerId);
            ClientReadManufacturerActivity clientReadManufacturerActivity = new ClientReadManufacturerActivity(manufacturerURI);
            clientReadManufacturerActivity.readManufacturer();
            manufacturer = clientReadManufacturerActivity.getManufacturer();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return manufacturer;
    }

}
