package com.xpower.xhomeremote.data.socket;

import com.xpower.xhomeremote.data.model.SocketDTO;

import java.util.List;

public interface IWebSocketCallback {
    void internalConnectionFailed();
    void externalConnectionFailed();
    void receiveSockets(List<SocketDTO> sockets);
}

