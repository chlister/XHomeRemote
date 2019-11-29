/**
 * This enum represent the types a Outlet can have
 *
 * @author Martin J. J.
 * @version 1.0
 * @since 11/20/2019
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

    public HomeApplianceType(String name, int onRessource, int offRessource) {
        this.name = name;
        this.onRessource = onRessource;
        this.offRessource = offRessource;
    }

    public static List<HomeApplianceType> getHomeAppliancesTypes(){
        List<HomeApplianceType> list = new ArrayList<>();
        list.add(new HomeApplianceType("other", R.drawable.ic_outlet_on, R.drawable.ic_outlet_off));
        list.add(new HomeApplianceType("Coffee Machine", R.drawable.ic_coffee_on, R.drawable.ic_coffee_off));
        list.add(new HomeApplianceType("laptop", R.drawable.ic_laptop_on, R.drawable.ic_laptop_off));
        list.add(new HomeApplianceType("light", R.drawable.ic_light_on, R.drawable.ic_light_off));
        return list;
    }

    public static HomeApplianceType getNonType(){
        return new HomeApplianceType("NON", R.drawable.ic_outlet_on, R.drawable.ic_outlet_off);
    }

    public static HomeApplianceType getType(String name){
        for (HomeApplianceType h : getHomeAppliancesTypes()){
            if(h.name.equals(name))
                return h;
        }
        return getNonType();
    }

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