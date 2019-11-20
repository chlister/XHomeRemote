/**
 * This Class is a data transfer object the represent en unique socket
 *
 * @author Martin J. J.
 * @version 1.0
 * @since 11/20/2019
 */
package com.xpower.xhomeremote.data.model;



public class SocketDTO {
    public int id;
    public int agentId;
    public String name;
    public HomeApplianceType type;

    @Override
    public String toString() {
        return "SocketDTO{" +
                "id=" + id +
                ", agentId=" + agentId +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
