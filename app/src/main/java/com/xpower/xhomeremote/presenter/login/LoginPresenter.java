package com.xpower.xhomeremote.presenter.login;

import com.xpower.xhomeremote.data.model.SocketDTO;
import com.xpower.xhomeremote.data.websocket.IWebSocketCallback;
import com.xpower.xhomeremote.data.websocket.IWebSocketManager;
import com.xpower.xhomeremote.data.websocket.WebSocketManager;
import com.xpower.xhomeremote.ui.login.ILoginView;

import java.util.List;

import okhttp3.WebSocket;

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
        mWebSocketManager.setIps("wss://echo.websocket.org", "wss://echo.websocket.org");
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
