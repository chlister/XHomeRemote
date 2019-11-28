package com.xpower.xhomeremote.data.websocket;

import com.xpower.xhomeremote.data.model.Socket;

import java.util.List;

import okhttp3.WebSocket;

public interface IWebSocketCallback {
    void receiveSockets(List<Socket> sockets);

    void registerconnection(boolean success);

    void internalConnectionFailed();

    void externalConnectionFailed();

    void connectEstablis(WebSocket wSocket);
}

