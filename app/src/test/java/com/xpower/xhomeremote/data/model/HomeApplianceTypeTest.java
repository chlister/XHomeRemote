package com.xpower.xhomeremote.data.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class HomeApplianceTypeTest {

    @Test
    public void getType() {

        // Test that unknownTypes returns the NonType
        Assert.assertEquals(HomeApplianceType.getNonType().name, HomeApplianceType.getType("UnknownType").name);

        // Test that the correct Type is returned
        Assert.assertEquals("laptop", HomeApplianceType.getType("laptop").name);

        // Test that getType is Case-Sensitive
        Assert.assertNotEquals("laptop", HomeApplianceType.getType("Laptop"));
    }
}