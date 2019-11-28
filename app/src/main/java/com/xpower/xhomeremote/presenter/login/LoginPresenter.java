package com.xpower.xhomeremote.presenter.login;

import com.xpower.xhomeremote.data.websocket.IWebSocketManager;
import com.xpower.xhomeremote.data.websocket.WebSocketManager;
import com.xpower.xhomeremote.ui.login.ILoginView;

public class LoginPresenter implements ILoginPresenter, ILoginPresenterCallback {
    private IWebSocketManager mWebSocketManager;
    private ILoginView mView;

    public LoginPresenter(ILoginView view){
        mView = view;
        mWebSocketManager = WebSocketManager.getInstance();
        mWebSocketManager.setFailedCallback(this);
        mWebSocketManager.setSuccesCallback(this);
    }

    @Override
    public void establishConnection(String user, String password){
        mWebSocketManager.setIps("ws://192.168.1.127:80/x/home", "wss://echo.websocket.org");
        mWebSocketManager.startSocketConnection();
    }

    @Override
    public void websocketConnectionFailed() {

    }

    @Override
    public void websocketConnectionSucces() {
        mView.connectionSuccess();
    }
}
