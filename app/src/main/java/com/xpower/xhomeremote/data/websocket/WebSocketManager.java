package com.xpower.xhomeremote.data.websocket;

import com.xpower.message.Message;
import com.xpower.message.MethodCode;
import com.xpower.message.model.OutletDTO;
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
    private IWebsocketConnectionSuccess mSuccessCallback;
    private IWebsocketConnectionFailed mFailedCallback;
    private IWebsocketReceiveOutlet mReceiveSocketCallback;



    private IWebsocketRegister mRegisterCallback;

    private WebSocketListener mWebSocketListener;
    private OkHttpClient mClient;
    private String mInternIp, mExternIp;
    public WebSocket mWebSocketConnection;
    private boolean internalFailed;

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
    public void setSuccessCallback(IWebsocketConnectionSuccess mSuccessCallback) {
        this.mSuccessCallback = mSuccessCallback;
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
        internalFailed = false;
        if(mWebSocketConnection == null ) {
            if (mInternIp != null) {
                mClient.newWebSocket(new Request.Builder().url(mInternIp).build(), mWebSocketListener);
            }
        }
        else{
            mSuccessCallback.onWebsocketConnectionSuccess();
        }

    }

    @Override
    public void getOutlets() {
        Message m = new Message(null, MethodCode.GET_SOCKETS, null);
        if(mWebSocketConnection != null)
            mWebSocketConnection.send(m.encode());
    }

    @Override
    public void registerOutlet(Outlet outlet) {
        OutletDTO data = new OutletDTO(outlet.id, outlet.agentId, outlet.name, outlet.type.name, outlet.state);
        Message m = new Message(null, MethodCode.REGISTER, data );
        if(mWebSocketConnection != null)
            mWebSocketConnection.send(m.encode());
    }

    @Override
    public void updateOutlet(Outlet outlet) {
        OutletDTO data = new OutletDTO(outlet.id, outlet.agentId, outlet.name, outlet.type.name, outlet.state);
        Message m = new Message(null, MethodCode.CHANGE_SOCKET_STATE, data); //Todo: change methodCode
        if(mWebSocketConnection != null)
            mWebSocketConnection.send(m.encode());
    }

    @Override
    public void setIps(String internal, String external) {
        mInternIp = internal;
        mExternIp = external;
    }

    @Override
    public void onInternalConnectionFailed() {
        if(internalFailed){
            onExternalConnectionFailed();
            return;
        }
        internalFailed = true;
        if(mInternIp != null){
            mClient.newWebSocket(new Request.Builder().url(mExternIp).build(),mWebSocketListener );
        }
    }

    @Override
    public void onExternalConnectionFailed() {
        mFailedCallback.onWebsocketConnectionFailed("");
    }

    @Override
    public void onConnectionSucces(WebSocket wSocket) {
        mWebSocketConnection = wSocket;
        mSuccessCallback.onWebsocketConnectionSuccess();
    }


    @Override
    public void onReceiveSockets(List<OutletDTO> sockets) {
        List<Outlet> list = new ArrayList<>();
        for (OutletDTO s: sockets) {
            list.add(new Outlet(s.getId(), s.getAgentId(), s.getName(), HomeApplianceType.getType(s.getApplianceType()), s.getState()));
        }
        mReceiveSocketCallback.onReceiveOutlet(list);
    }

    @Override
    public void onRegisterSuccess() {
        mRegisterCallback.onRegisterSuccess();
    }

    @Override
    public void onRegisterFailed() {
        mRegisterCallback.onRegisterFailed("");
    }

}
