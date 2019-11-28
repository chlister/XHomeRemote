package com.xpower.xhomeremote.presenter.outletlist;

import com.xpower.xhomeremote.data.websocket.callback.IWebsocketConnectionFailed;
import com.xpower.xhomeremote.data.websocket.callback.IWebsocketConnectionSuccess;
import com.xpower.xhomeremote.data.websocket.callback.IWebsocketReceiveOutlet;

public interface IOutletListPresenterCallback extends IWebsocketConnectionFailed, IWebsocketConnectionSuccess, IWebsocketReceiveOutlet {
}
