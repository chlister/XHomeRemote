package com.xpower.xhomeremote.data.websocket;

import com.xpower.xhomeremote.data.model.Socket;
import com.xpower.xhomeremote.data.websocket.callback.IWebsocketConnectionFailed;
import com.xpower.xhomeremote.data.websocket.callback.IWebsocketConnectionSuccess;
import com.xpower.xhomeremote.data.websocket.callback.IWebsocketReceiveSockets;
import com.xpower.xhomeremote.data.websocket.callback.IWebsocketRegister;

public interface IWebSocketManager {
    void setSuccesCallback(IWebsocketConnectionSuccess mSuccesCallback);
    void setFailedCallback(IWebsocketConnectionFailed mFailedCallback);
    void setReceiveSocketCallback(IWebsocketReceiveSockets mReceiveSocketCallback);
    void setRegisterCallback(IWebsocketRegister mRegisterCallback);
    
    void startSocketConnection();
    void getSockets();
    void registerSocket(Socket socket);
    void setIps(String internal, String external);
}
