/**
 * This Class is a data transfer object the represent en unique socket
 *
 * @author Martin J. J.
 * @version 1.0
 * @since 11/20/2019
 */
package com.xpower.xhomeremote.data.model;


import java.io.Serializable;

public class Socket implements Serializable {
    public int id;
    public int agentId;
    public String name;
    public HomeApplianceType type;

    public Socket(int id, int agentId, String name, HomeApplianceType type) {
        this.id = id;
        this.agentId = agentId;
        this.name = name;
        this.type = type;

    }

    @Override
    public String toString() {
        return "Socket{" +
                "id=" + id +
                ", agentId=" + agentId +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
