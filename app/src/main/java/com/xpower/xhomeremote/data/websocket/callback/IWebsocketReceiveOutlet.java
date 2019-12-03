/**
 * @author Martin J. J.
 * @version 1.0
 * @since 11/26/2019
 */
package com.xpower.xhomeremote.data.websocket.callback;

import com.xpower.xhomeremote.data.model.Outlet;

import java.util.List;

public interface IWebsocketReceiveOutlet {
    void onReceiveOutlet(List<Outlet> outlets);
}
