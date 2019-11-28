package com.xpower.xhomeremote.data.websocket;

import com.xpower.message.Message;
import com.xpower.message.MethodCode;
import com.xpower.message.RespondCodes;
import com.xpower.message.model.SocketDTO;
import com.xpower.xhomeremote.data.model.HomeApplianceType;
import com.xpower.xhomeremote.data.model.Socket;
import com.xpower.xhomeremote.data.websocket.callback.IWebsocketConnectionFailed;
import com.xpower.xhomeremote.data.websocket.callback.IWebsocketConnectionSuccess;
import com.xpower.xhomeremote.data.websocket.callback.IWebsocketReceiveSockets;
import com.xpower.xhomeremote.data.websocket.callback.IWebsocketRegister;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class WebSocketManager implements IWebSocketCallback, IWebSocketManager {
    private static WebSocketManager instance = null;
    private IWebsocketConnectionSuccess mSuccesCallback;
    private IWebsocketConnectionFailed mFailedCallback;

    private IWebsocketReceiveSockets mReceiveSocketCallback;



    private IWebsocketRegister mRegisterCallback;

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
    public void setRegisterCallback(IWebsocketRegister mRegisterCallback) {
        this.mRegisterCallback = mRegisterCallback;
    }

    @Override
    public void startSocketConnection(){
        if(mInternIp != null){
            mClient.newWebSocket(new Request.Builder().url(mInternIp).build(),mWebSocketListener );
        }
    }

    @Override
    public void getSockets() {
        Message m = new Message(null, MethodCode.GET_SOCKETS, null);
        if(mWebSocketConnection != null)
            mWebSocketConnection.send(m.encode());
    }

    @Override
    public void registerSocket(Socket socket) {
        SocketDTO data = new SocketDTO(socket.id, socket.agentId, socket.name, socket.type.name());
        Message m = new Message(null, MethodCode.REGISTER, data );
        if(mWebSocketConnection != null)
            mWebSocketConnection.send(m.encode());
    }

    @Override
    public void setIps(String internal, String external) {
        mInternIp = internal;
        mExternIp = external;
    }

    @Override
    public void internalConnectionFailed() {
        if(mInternIp != null){
            mClient.newWebSocket(new Request.Builder().url(mExternIp).build(),mWebSocketListener );
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
    public void receiveSockets(List<Socket> sockets) {
        mReceiveSocketCallback.receiveSockets(sockets);
    }

    @Override
    public void registerconnection(boolean success) {
        if(success)
            mRegisterCallback.onRegisterComplete();
        else
            mFailedCallback.websocketConnectionFailed(); //TODO: custom callback
    }

}
