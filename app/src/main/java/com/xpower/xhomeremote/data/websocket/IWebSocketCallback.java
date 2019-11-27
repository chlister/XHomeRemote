package com.xpower.xhomeremote.data.websocket;

import com.xpower.xhomeremote.data.model.SocketDTO;

import java.util.List;

import okhttp3.WebSocket;

public interface IWebSocketCallback {
    void receiveSockets(List<SocketDTO> sockets);

    void internalConnectionFailed();

    void externalConnectionFailed();

    void connectEstablis(WebSocket wSocket);
}

