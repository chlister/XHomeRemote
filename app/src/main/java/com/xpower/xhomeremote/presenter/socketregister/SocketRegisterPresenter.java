package com.xpower.xhomeremote.presenter.socketregister;

import com.xpower.xhomeremote.data.model.Socket;
import com.xpower.xhomeremote.data.websocket.IWebSocketManager;
import com.xpower.xhomeremote.data.websocket.WebSocketManager;
import com.xpower.xhomeremote.ui.socketregister.ISocketRegisterView;

public class SocketRegisterPresenter implements ISocketRegisterPresneterCallback, ISocketRegisterPresenter {
    private ISocketRegisterView mView;
    private IWebSocketManager mWebSocketManager;

    public SocketRegisterPresenter(ISocketRegisterView mView) {
        this.mView = mView;
        mWebSocketManager = WebSocketManager.getInstance();
        mWebSocketManager.setRegisterCallback(this);
    }

    @Override
    public void registerSocket(Socket socket){
        mWebSocketManager.registerSocket(socket);
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
