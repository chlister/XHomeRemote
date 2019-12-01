package com.xpower.xhomeremote.data.websocket.callback;

public interface IWebsocketRegister {
    void onRegisterSuccess();
    void onRegisterFailed(String msg);
}
