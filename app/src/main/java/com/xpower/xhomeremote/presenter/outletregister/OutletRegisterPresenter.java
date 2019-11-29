package com.xpower.xhomeremote.presenter.outletregister;

import com.xpower.xhomeremote.data.model.Outlet;
import com.xpower.xhomeremote.data.websocket.IWebSocketManager;
import com.xpower.xhomeremote.data.websocket.WebSocketManager;
import com.xpower.xhomeremote.ui.outletregister.IOutletRegisterView;

public class OutletRegisterPresenter implements IOutletRegisterPresneterCallback, IOutletRegisterPresenter {
    private IOutletRegisterView mView;
    private IWebSocketManager mWebSocketManager;

    public OutletRegisterPresenter(IOutletRegisterView mView) {
        this.mView = mView;
        mWebSocketManager = WebSocketManager.getInstance();
        mWebSocketManager.setRegisterCallback(this);
    }

    @Override
    public void registerOutlet(Outlet outlet){
        mWebSocketManager.registerOutlet(outlet);
    }

    @Override
    public void onRegisterComplete() {
        mView.registerSuccesfull();
    }

    @Override
    public void websocketConnectionFailed() {
        mView.registerFailed();
    }
}
