package com.gefa.ekf.client;


import com.gefa.ekf.domain.TestAssetFactory;
import com.gefa.invf.client.activities.Actions;
import com.gefa.invf.client.activities.ClientCreateAssetActivity;
import com.gefa.invf.client.activities.ClientReadAssetActivity;
import com.gefa.invf.client.domain.Asset;
import com.gefa.invf.client.exceptions.NotFoundException;
import com.gefa.invf.client.exceptions.ServiceFailureException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;


public class AssetTest {

    @BeforeClass
    public static void init() {
//        MyServer.startContainer();
    }

    @AfterClass
    public static void stop() {
  //      MyServer.stopContainer();

    }

    private String getEntryPointURI() {
        return "http://localhost:8086/inv-fin-service/v0.0.0/asset";
    }

//	private String getEntryPointURI() {
//		return "http://localhost:8080/ekf-tool-ddd-rest/asset";
//	}

    public void testCreateAsset() throws URISyntaxException, NotFoundException, ServiceFailureException {
        ClientCreateAssetActivity clientCreateAssetActivity = new ClientCreateAssetActivity();

        TestAssetFactory testAssetFactory = new TestAssetFactory();
        Asset asset = testAssetFactory.createAsset();

        clientCreateAssetActivity.createAsset(asset, new URI(getEntryPointURI()));

        Asset createdAsset = null;
        Actions actions = clientCreateAssetActivity.getActions();

        if (actions.has(ClientReadAssetActivity.class)) {
            ClientReadAssetActivity clientReadAssetActivity = actions.get(ClientReadAssetActivity.class);
            clientReadAssetActivity.readAsset();
            createdAsset = clientReadAssetActivity.getAsset();
            actions = clientReadAssetActivity.getActions();
        }
        Assert.assertEquals(asset.getAssetName(), createdAsset.getAssetName());
        Assert.assertEquals(asset.getManufacturerId(), createdAsset.getManufacturerId());
        Assert.assertEquals(asset.getObjectId(), createdAsset.getObjectId());

    }

    @Test
    public void testGetAsset() throws URISyntaxException {
        URI assetURI = new URI(getEntryPointURI()+"/1");
        ClientReadAssetActivity clientReadAssetActivity = new ClientReadAssetActivity(assetURI);
        Asset asset =  clientReadAssetActivity.readAsset();
//        Assert.assertEquals(new Long(0), asset.getId());
        Assert.assertEquals(new Long(1), asset.getManufacturerId());
        Assert.assertEquals(new Long(1), asset.getObjectId());
    }

}
