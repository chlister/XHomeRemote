package com.xpower.xhomeremote.presenter.outletregister;

import com.xpower.xhomeremote.data.model.Outlet;
import com.xpower.xhomeremote.data.websocket.IWebSocketManager;
import com.xpower.xhomeremote.data.websocket.WebSocketManager;
import com.xpower.xhomeremote.ui.outletregister.IOutletRegisterView;

public class OutletRegisterPresenter implements IOutletRegisterPresneterCallback, IOutletRegisterPresenter {
    private IOutletRegisterView mView;
    private IWebSocketManager mWebSocketManager;

    public OutletRegisterPresenter(IOutletRegisterView mView, IWebSocketManager webSocketManager) {
        this.mView = mView;
        mWebSocketManager = webSocketManager;
        mWebSocketManager.setRegisterCallback(this);
    }

    @Override
    public void registerOutlet(Outlet outlet){
        mWebSocketManager.registerOutlet(outlet);
    }

    @Override
    public void onRegisterSuccess() {
        mView.onRegisterSuccess();
    }

    @Override
    public void onRegisterFailed(String msg) {
        mView.onRegisterFailed();
    }

    @Override
    public void onWebsocketConnectionFailed(String msg) {
        mView.onConnectionFailed(msg);
    }
}
