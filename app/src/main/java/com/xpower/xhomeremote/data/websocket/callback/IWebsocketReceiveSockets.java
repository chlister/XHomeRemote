package com.xpower.xhomeremote.data.websocket.callback;

import com.xpower.xhomeremote.data.model.SocketDTO;

import java.util.List;

public interface IWebsocketReceiveSockets {
    void receiveSockets(List<SocketDTO> sockets);
}
