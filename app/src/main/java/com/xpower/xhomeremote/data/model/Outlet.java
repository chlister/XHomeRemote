/**
 * This Class represent the Outlet.
 *
 * @author Martin J. J.
 * @version 1.0
 * @since 11/20/2019
 */
package com.xpower.xhomeremote.data.model;


import java.io.Serializable;

public class Outlet implements Serializable {
    public int id;
    public int agentId;
    public String name;
    public HomeApplianceType type;
    public boolean state;

    /**
     * @author  Martin J. J.
     * @since   11/20/2019
     * @status  Done
     */
    public Outlet(int id, int agentId, String name, HomeApplianceType type, boolean state) {
        this.id = id;
        this.agentId = agentId;
        this.name = name;
        this.type = type;
        this.state = state;
    }

    /**
     * @author  Martin J. J.
     * @since   11/20/2019
     * @status  Done
     */
    @Override
    public String toString() {
        return "Outlet{" +
                "id=" + id +
                ", agentId=" + agentId +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", state=" + state +
                '}';
    }
}
