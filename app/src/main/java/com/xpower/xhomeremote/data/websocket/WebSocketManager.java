package com.xpower.xhomeremote.data.websocket;

import com.xpower.message.Message;
import com.xpower.message.MethodCode;
import com.xpower.message.model.SocketDTO;
import com.xpower.xhomeremote.data.model.HomeApplianceType;
import com.xpower.xhomeremote.data.model.Outlet;
import com.xpower.xhomeremote.data.websocket.callback.IWebsocketConnectionFailed;
import com.xpower.xhomeremote.data.websocket.callback.IWebsocketConnectionSuccess;
import com.xpower.xhomeremote.data.websocket.callback.IWebsocketReceiveOutlet;
import com.xpower.xhomeremote.data.websocket.callback.IWebsocketRegister;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class WebSocketManager implements IWebSocketCallback, IWebSocketManager {
    private static WebSocketManager instance = null;
    private IWebsocketConnectionSuccess mSuccesCallback;
    private IWebsocketConnectionFailed mFailedCallback;

    private IWebsocketReceiveOutlet mReceiveSocketCallback;



    private IWebsocketRegister mRegisterCallback;

    private WebSocketListener mWebSocketListener;
    private OkHttpClient mClient;
    private String mInternIp, mExternIp;
    public WebSocket mWebSocketConnection;

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
    public void setReceiveOutletCallback(IWebsocketReceiveOutlet mReceiveSocketCallback) {
        this.mReceiveSocketCallback = mReceiveSocketCallback;
    }

    @Override
    public void setRegisterCallback(IWebsocketRegister mRegisterCallback) {
        this.mRegisterCallback = mRegisterCallback;
    }

    @Override
    public void startSocketConnection(){
        if(mWebSocketConnection == null ) {
            if (mInternIp != null) {
                mClient.newWebSocket(new Request.Builder().url(mInternIp).build(), mWebSocketListener);
            }
        }
        else{
            mSuccesCallback.websocketConnectionSucces();
        }

    }

    @Override
    public void getOutlets() {
        Message m = new Message(null, MethodCode.GET_SOCKETS, null);
        if(mWebSocketConnection != null)
            mWebSocketConnection.send(m.encode());
    }

    @Override
    public void registerSocket(Outlet outlet) {
        SocketDTO data = new SocketDTO(outlet.id, outlet.agentId, outlet.name, outlet.type.name());
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
    public void receiveSockets(List<SocketDTO> sockets) {
        List<Outlet> list = new ArrayList<>();
        for (SocketDTO s: sockets) {
            list.add(new Outlet(s.getId(), s.getAgentId(), s.getName(), HomeApplianceType.valueOf(s.getApplianceType())));
        }
        mReceiveSocketCallback.receiveOutlets(list);
    }

    @Override
    public void registerconnection(boolean success) {
        if(success)
            mRegisterCallback.onRegisterComplete();
        else
            mFailedCallback.websocketConnectionFailed(); //TODO: custom callback
    }

}
