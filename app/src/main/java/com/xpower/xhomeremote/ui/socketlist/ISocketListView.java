/**
 * This is the callback interface that are injected to the presenter
 *
 * @author Martin J. J.
 * @version 1.0
 * @since 11/20/2019
 */
package com.xpower.xhomeremote.ui.socketlist;

import com.xpower.xhomeremote.data.model.SocketDTO;

import java.util.List;

public interface ISocketListView {
    void updateSocketList(List<SocketDTO> sockets);
}
