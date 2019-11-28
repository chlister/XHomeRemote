package com.xpower.xhomeremote.data.websocket.callback;

import com.xpower.xhomeremote.data.model.Socket;

import java.util.List;

public interface IWebsocketReceiveSockets {
    void receiveSockets(List<Socket> sockets);
}
