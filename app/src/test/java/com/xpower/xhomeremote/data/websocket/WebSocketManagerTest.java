package com.xpower.xhomeremote.data.websocket;

import com.xpower.message.model.OutletDTO;
import com.xpower.xhomeremote.data.model.HomeApplianceType;
import com.xpower.xhomeremote.data.model.Outlet;
import com.xpower.xhomeremote.data.websocket.callback.IWebsocketReceiveOutlet;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class WebSocketManagerTest {

    public static final String OBJECT1_NAME = "Kitchen";
    public static final int OBJECT1_ID = 22;
    public static final boolean OBJECT1_STATE = false;
    public static final int OBJECT1_AGENTID = 11;
    public static final String OBJECT1_TYPENAME = "light";

    public static final String OBJECT2_NAME = "Citron kage";
    public static final int OBJECT2_ID = 21;
    public static final boolean OBJECT2_STATE = false;
    public static final int OBJECT2_AGENTID = 10;
    public static final String OBJECT2_UNKNOWN_TYPENAME = "kage" ;

    @Test
    public void onReceiveOutlets() {
        WebSocketManager manager = WebSocketManager.getInstance();


        manager.setReceiveOutletCallback(new IWebsocketReceiveOutlet() {
            @Override
            public void onReceiveOutlet(List<Outlet> outlets) {
                // Test the both items has been converted
                Assert.assertEquals(2, outlets.size());

                // Test the first Items (known type)
                Assert.assertEquals(OBJECT1_NAME,outlets.get(0).name);
                Assert.assertEquals(OBJECT1_ID, outlets.get(0).id);
                Assert.assertEquals(OBJECT1_STATE, outlets.get(0).state);
                Assert.assertEquals(OBJECT1_AGENTID, outlets.get(0).agentId);
                Assert.assertEquals(OBJECT1_TYPENAME, outlets.get(0).type.name);

                // Test the second item (unknown type)
                Assert.assertEquals(OBJECT2_NAME,outlets.get(1).name);
                Assert.assertEquals(OBJECT2_ID, outlets.get(1).id);
                Assert.assertEquals(OBJECT2_STATE, outlets.get(1).state);
                Assert.assertEquals(OBJECT2_AGENTID, outlets.get(1).agentId);
                Assert.assertEquals(HomeApplianceType.getNonType().name, outlets.get(1).type.name);
            }
        });
        List<OutletDTO> list = new ArrayList<>();

        OutletDTO testObject = new OutletDTO();
        testObject.setName(OBJECT1_NAME);
        testObject.setId(OBJECT1_ID);
        testObject.setState(OBJECT1_STATE);
        testObject.setAgentId(OBJECT1_AGENTID);
        testObject.setApplianceType(OBJECT1_TYPENAME);

        OutletDTO testObject2 = new OutletDTO();
        testObject2.setName(OBJECT2_NAME);
        testObject2.setId(OBJECT2_ID);
        testObject2.setState(OBJECT2_STATE);
        testObject2.setAgentId(OBJECT2_AGENTID);
        testObject2.setApplianceType(OBJECT2_UNKNOWN_TYPENAME);

        list.add(testObject);
        list.add(testObject2);
        manager.onReceiveOutlets(list);
    }
}