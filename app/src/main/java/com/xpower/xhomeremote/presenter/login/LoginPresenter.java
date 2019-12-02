package com.xpower.xhomeremote.presenter.login;

import com.xpower.xhomeremote.data.websocket.IWebSocketManager;
import com.xpower.xhomeremote.data.websocket.WebSocketManager;
import com.xpower.xhomeremote.ui.login.ILoginView;

public class LoginPresenter implements ILoginPresenter, ILoginPresenterCallback {
    private IWebSocketManager mWebSocketManager;
    private ILoginView mView;

    public LoginPresenter(ILoginView view, IWebSocketManager webSocketManager){
        mView = view;
        mWebSocketManager = webSocketManager;
        mWebSocketManager.setFailedCallback(this);
        mWebSocketManager.setSuccessCallback(this);
    }

    @Override
    public void login(String user, String password){
        mWebSocketManager.setIps(user, password);
        mWebSocketManager.startSocketConnection();
    }



    @Override
    public void onWebsocketConnectionSuccess() {
        mView.onConnectionSuccess();
    }

    @Override
    public void onWebsocketConnectionFailed(String msg) {
        mView.onConnectionFailed(msg);
    }
}
