package com.xpower.xhomeremote.data.websocket;

import com.xpower.message.model.OutletDTO;

import java.util.List;

import okhttp3.WebSocket;

public interface IWebSocketCallback  {
    void onReceiveSockets(List<OutletDTO> sockets);

    void onRegisterSuccess();

    void onRegisterFailed();

    void onInternalConnectionFailed();

    void onExternalConnectionFailed();

    void onConnectionSucces(WebSocket wSocket);
}

