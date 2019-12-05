/**
 * @author Martin J. J.
 * @version 1.0
 * @since 11/22/2019
 */
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

    /**
     * @author  Martin J. J.
     * @since   11/22/2019
     * @status  Done
     */
    public WebSocketManager(){
        mWebSocketListener = new WebSocketListener(this);
        mClient = new OkHttpClient();
    }

    /**
     * @author  Martin J. J.
     * @since   11/22/2019
     * @status  Done
     */
    public static WebSocketManager getInstance(){
        if(instance == null)
            instance = new WebSocketManager();
        return instance;
    }

    /**
     * @author  Martin J. J.
     * @since   11/22/2019
     * @status  Done
     */
    @Override
    public void setSuccessCallback(IWebsocketConnectionSuccess mSuccessCallback) {
        this.mSuccessCallback = mSuccessCallback;
    }

    /**
     * @author  Martin J. J.
     * @since   11/22/2019
     * @status  Done
     */
    @Override
    public void setFailedCallback(IWebsocketConnectionFailed mFailedCallback) {
        this.mFailedCallback = mFailedCallback;
    }

    /**
     * @author  Martin J. J.
     * @since   11/22/2019
     * @status  Done
     */
    @Override
    public void setReceiveOutletCallback(IWebsocketReceiveOutlet mReceiveSocketCallback) {
        this.mReceiveSocketCallback = mReceiveSocketCallback;
    }

    /**
     * @author  Martin J. J.
     * @since   11/22/2019
     * @status  Done
     */
    @Override
    public void setRegisterCallback(IWebsocketRegister mRegisterCallback) {
        this.mRegisterCallback = mRegisterCallback;
    }

    /**
     * @author  Martin J. J.
     * @since   11/22/2019
     * @status  Done
     */
    @Override
    public void startSocketConnection(){
        internalFailed = false;

        if (mInternIp != null) {
            mClient.newWebSocket(new Request.Builder().url(mInternIp).build(), mWebSocketListener);
        }
        else{
            mSuccessCallback.onWebsocketConnectionSuccess();
        }

    }

    /**
     * @author  Martin J. J.
     * @since   11/22/2019
     * @status  Done
     */
    @Override
    public void getOutlets() {
        Message m = new Message(null, MethodCode.GET_OUTLETS, null);
        if(mWebSocketConnection != null)
            mWebSocketConnection.send(m.encode());
    }

    /**
     * @author  Martin J. J.
     * @since   11/22/2019
     * @status  Done
     */
    @Override
    public void registerOutlet(Outlet outlet) {
        OutletDTO data = new OutletDTO(outlet.id, outlet.agentId, outlet.name, outlet.type.name, outlet.state);
        Message m = new Message(null, MethodCode.REGISTER, data );
        if(mWebSocketConnection != null)
            mWebSocketConnection.send(m.encode());
    }

    /**
     * @author  Martin J. J.
     * @since   11/22/2019
     * @status  Done
     */
    @Override
    public void updateOutlet(Outlet outlet) {
        OutletDTO data = new OutletDTO(outlet.id, outlet.agentId, outlet.name, outlet.type.name, outlet.state);
        Message m = new Message(null, MethodCode.CHANGE_OUTLET_STATE, data);
        if(mWebSocketConnection != null)
            mWebSocketConnection.send(m.encode());
    }

    /**
     * @author  Martin J. J.
     * @since   11/22/2019
     * @status  Done
     */
    @Override
    public void setIps(String internal, String external) {
        mInternIp = internal;
        mExternIp = external;
    }

    /**
     * @author  Martin J. J.
     * @since   11/22/2019
     * @status  Done
     */
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

    /**
     * @author  Martin J. J.
     * @since   11/22/2019
     * @status  Done
     */
    @Override
    public void onExternalConnectionFailed() {
        mFailedCallback.onWebsocketConnectionFailed("");
    }

    /**
     * @author  Martin J. J.
     * @since   11/22/2019
     * @status  Done
     */
    @Override
    public void onConnectionSucces(WebSocket wSocket) {
        mWebSocketConnection = wSocket;
        mSuccessCallback.onWebsocketConnectionSuccess();
    }

    /**
     * @author  Martin J. J.
     * @since   11/22/2019
     * @status  Done
     */
    @Override
    public void onReceiveOutlets(List<OutletDTO> outletsDTO) {
        List<Outlet> list = new ArrayList<>();
        for (OutletDTO s: outletsDTO) {
            list.add(new Outlet(s.getId(), s.getAgentId(), s.getName(), HomeApplianceType.getType(s.getApplianceType()), s.getState()));
        }
        mReceiveSocketCallback.onReceiveOutlet(list);
    }

    /**
     * @author  Martin J. J.
     * @since   11/22/2019
     * @status  Done
     */
    @Override
    public void onRegisterSuccess() {
        mRegisterCallback.onRegisterSuccess();
    }

    /**
     * @author  Martin J. J.
     * @since   11/22/2019
     * @status  Done
     */
    @Override
    public void onRegisterFailed() {
        mRegisterCallback.onRegisterFailed("");
    }

}
