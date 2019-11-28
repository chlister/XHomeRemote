package com.xpower.xhomeremote.presenter.socketregister;

import com.xpower.xhomeremote.data.websocket.callback.IWebsocketConnectionFailed;
import com.xpower.xhomeremote.data.websocket.callback.IWebsocketRegister;

public interface ISocketRegisterPresneterCallback extends IWebsocketRegister, IWebsocketConnectionFailed {
}
