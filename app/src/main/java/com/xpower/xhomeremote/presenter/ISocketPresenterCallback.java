package com.xpower.xhomeremote.presenter;

import com.xpower.xhomeremote.data.model.SocketDTO;

import java.util.List;

public interface ISocketPresenterCallback {
    void receiveSockets(List<SocketDTO> sockets);
    void websocketConnectionFailed();
}
