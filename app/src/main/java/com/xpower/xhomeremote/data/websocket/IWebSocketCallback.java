package com.xpower.xhomeremote.data.websocket;

import com.xpower.message.model.OutletDTO;

import java.util.List;

import okhttp3.WebSocket;

public interface IWebSocketCallback {
    void receiveSockets(List<OutletDTO> sockets);

    void registerconnection(boolean success);

    void internalConnectionFailed();

    void externalConnectionFailed();

    void connectEstablis(WebSocket wSocket);
}

