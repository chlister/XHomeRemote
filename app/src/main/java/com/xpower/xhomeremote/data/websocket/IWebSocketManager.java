package com.xpower.xhomeremote.data.websocket;

import com.xpower.xhomeremote.data.model.Outlet;
import com.xpower.xhomeremote.data.websocket.callback.IWebsocketConnectionFailed;
import com.xpower.xhomeremote.data.websocket.callback.IWebsocketConnectionSuccess;
import com.xpower.xhomeremote.data.websocket.callback.IWebsocketReceiveOutlet;
import com.xpower.xhomeremote.data.websocket.callback.IWebsocketRegister;

public interface IWebSocketManager {
    void setSuccesCallback(IWebsocketConnectionSuccess mSuccesCallback);
    void setFailedCallback(IWebsocketConnectionFailed mFailedCallback);
    void setReceiveOutletCallback(IWebsocketReceiveOutlet mReceiveSocketCallback);
    void setRegisterCallback(IWebsocketRegister mRegisterCallback);
    
    void startSocketConnection();
    void getOutlets();
    void registerOutlet(Outlet outlet);
    void updateOutelet(Outlet outlet);
    void setIps(String internal, String external);
}
