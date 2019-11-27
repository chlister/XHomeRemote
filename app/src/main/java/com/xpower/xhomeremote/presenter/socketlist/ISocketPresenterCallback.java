package com.xpower.xhomeremote.presenter.socketlist;

import com.xpower.xhomeremote.data.model.SocketDTO;
import com.xpower.xhomeremote.data.websocket.callback.IWebsocketConnectionFailed;
import com.xpower.xhomeremote.data.websocket.callback.IWebsocketConnectionSuccess;
import com.xpower.xhomeremote.data.websocket.callback.IWebsocketReceiveSockets;

import java.util.List;

public interface ISocketPresenterCallback extends IWebsocketConnectionFailed, IWebsocketConnectionSuccess, IWebsocketReceiveSockets {
}
