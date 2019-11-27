package com.xpower.xhomeremote.data.websocket;

import com.xpower.xhomeremote.data.model.SocketDTO;
import com.xpower.xhomeremote.data.websocket.callback.IWebsocketConnectionFailed;
import com.xpower.xhomeremote.data.websocket.callback.IWebsocketConnectionSuccess;
import com.xpower.xhomeremote.data.websocket.callback.IWebsocketReceiveSockets;
import com.xpower.xhomeremote.presenter.login.ILoginPresenterCallback;
import com.xpower.xhomeremote.presenter.socketlist.ISocketPresenter;
import com.xpower.xhomeremote.presenter.socketlist.ISocketPresenterCallback;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class WebSocketManager implements IWebSocketCallback, IWebSocketManager {
    private static WebSocketManager instance = null;
    private IWebsocketConnectionSuccess mSuccesCallback;
    private IWebsocketConnectionFailed mFailedCallback;

    private IWebsocketReceiveSockets mReceiveSocketCallback;

    private WebSocketListener mWebSocketListener;
    private OkHttpClient mClient;
    private String mInternIp, mExternIp;
    private WebSocket mWebSocketConnection;

    public WebSocketManager(){
        mWebSocketListener = new WebSocketListener(this);
        mClient = new OkHttpClient();
    }



    public static WebSocketManager getInstance(){
        if(instance == null)
            instance = new WebSocketManager();
        return instance;
    }

    @Override
    public void setSuccesCallback(IWebsocketConnectionSuccess mSuccesCallback) {
        this.mSuccesCallback = mSuccesCallback;
    }

    @Override
    public void setFailedCallback(IWebsocketConnectionFailed mFailedCallback) {
        this.mFailedCallback = mFailedCallback;
    }

    @Override
    public void setReceiveSocketCallback(IWebsocketReceiveSockets mReceiveSocketCallback) {
        this.mReceiveSocketCallback = mReceiveSocketCallback;
    }

    @Override
    public void startSocketConnection(){
        if(mInternIp != null){
            mClient.newWebSocket(new Request.Builder().url(mInternIp).build(),mWebSocketListener );
        }
    }

    @Override
    public void getSockets() {
        if(mWebSocketConnection != null)
            mWebSocketConnection.send("GETSTUFF"); //TODO:
    }

    @Override
    public void setIps(String internal, String external) {
        mInternIp = internal;
        mExternIp = external;
    }

    @Override
    public void internalConnectionFailed() {
        if(mInternIp != null){
            mClient.newWebSocket(new Request.Builder().url("wss://echo.websocket.org").build(),mWebSocketListener );
        }
    }

    @Override
    public void externalConnectionFailed() {
        mFailedCallback.websocketConnectionFailed();
    }

    @Override
    public void connectEstablis(WebSocket wSocket) {
        mWebSocketConnection = wSocket;
        mSuccesCallback.websocketConnectionSucces();
    }

    @Override
    public void receiveSockets(List<SocketDTO> sockets) {
        mReceiveSocketCallback.receiveSockets(sockets);
    }

}
