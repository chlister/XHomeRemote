package com.xpower.xhomeremote.data.websocket;

import com.xpower.xhomeremote.data.websocket.callback.IWebsocketConnectionFailed;
import com.xpower.xhomeremote.data.websocket.callback.IWebsocketConnectionSuccess;
import com.xpower.xhomeremote.data.websocket.callback.IWebsocketReceiveSockets;

public interface IWebSocketManager {
    void setSuccesCallback(IWebsocketConnectionSuccess mSuccesCallback);
    void setFailedCallback(IWebsocketConnectionFailed mFailedCallback);
    void setReceiveSocketCallback(IWebsocketReceiveSockets mReceiveSocketCallback);
    
    void startSocketConnection();
    void getSockets();
    void setIps(String internal, String external);
}
