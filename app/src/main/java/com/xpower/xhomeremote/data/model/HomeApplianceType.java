/**
 * This enum represent the types a Outlet can have
 *
 * @author Martin J. J.
 * @version 2.0
 * @since 11/26/2019
 */
package com.xpower.xhomeremote.data.model;

import com.xpower.xhomeremote.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomeApplianceType implements Serializable {
    public String name;
    public int onRessource;
    public int offRessource;

    /**
     * @author  Martin J. J.
     * @since   11/26/2019
     * @status  Done
     */
    public HomeApplianceType(String name, int onRessource, int offRessource) {
        this.name = name;
        this.onRessource = onRessource;
        this.offRessource = offRessource;
    }

    /**
     * Here all the HomeApplianceTypes are defined.
     * @author  Martin J. J.
     * @since   11/26/2019
     * @status  Done
     */
    public static List<HomeApplianceType> getHomeAppliancesTypes(){
        List<HomeApplianceType> list = new ArrayList<>();
        list.add(new HomeApplianceType("other", R.drawable.ic_outlet_on, R.drawable.ic_outlet_off));
        list.add(new HomeApplianceType("Coffee Machine", R.drawable.ic_coffee_on, R.drawable.ic_coffee_off));
        list.add(new HomeApplianceType("laptop", R.drawable.ic_laptop_on, R.drawable.ic_laptop_off));
        list.add(new HomeApplianceType("light", R.drawable.ic_light_on, R.drawable.ic_light_off));
        return list;
    }

    /**
     * getNonType is a HomeApplianceType for Outlets that has no type
     * @author  Martin J. J.
     * @since   11/26/2019
     * @status  Done
     */
    public static HomeApplianceType getNonType(){
        return new HomeApplianceType("NON", R.drawable.ic_outlet_on, R.drawable.ic_outlet_off);
    }

    /**
     * get HomeApplianceType base on name, and give NonType if type by name is not found
     * @author  Martin J. J.
     * @since   11/26/2019
     * @status  Done
     */
    public static HomeApplianceType getType(String name){
        for (HomeApplianceType h : getHomeAppliancesTypes()){
            if(h.name.equals(name))
                return h;
        }
        return getNonType();
    }

    /**
     * Return the list of appliance type names (used for dropdown in register)
     * @author  Martin J. J.
     * @since   11/26/2019
     * @status  Done
     */
    public static List<String> getHomeApplianceNames(){
        List<String> names = new ArrayList<>();
        for (HomeApplianceType h : getHomeAppliancesTypes()){
            names.add(h.name);
        }
        return names;
    }


}
/*

  NON,
    OTHER,
    COFFEE_MACHINE,
    LAPTOP,
    LIGHT
 */