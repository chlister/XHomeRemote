/**
 * TODO: Add class description
 *
 * @author Martin J. J.
 * @version 1.0
 * @since 11/20/2019
 */
package com.xpower.xhomeremote.data;

import com.xpower.xhomeremote.data.api.IAPICallback;
import com.xpower.xhomeremote.data.model.SocketDTO;
import com.xpower.xhomeremote.data.socket.IWebSocketCallback;
import com.xpower.xhomeremote.data.socket.WebSocketListener;
import com.xpower.xhomeremote.presenter.ISocketPresenterCallback;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class DataManager implements IWebSocketCallback, IAPICallback {
    private static DataManager instance = null;
    private ISocketPresenterCallback callback;

    private WebSocketListener mWebSocketListener;
    private OkHttpClient mClient;
    private String mInternIp, mExternIp;

    public DataManager(ISocketPresenterCallback callback){
        mWebSocketListener = new WebSocketListener(this);
        mClient = new OkHttpClient();
        this.callback = callback;
    }


    public static DataManager getInstance(ISocketPresenterCallback callback){
        if(instance == null)
            instance = new DataManager(callback);
        return instance;
    }

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Ready for review
     */
    public void startSocketConnection(){
        if(mInternIp != null){
            mClient.newWebSocket(new Request.Builder().url(mInternIp).build(),mWebSocketListener );
        }
    }

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Ready for review
     */
    @Override
    public void internalConnectionFailed() {
        if(mExternIp != null){
            mClient.newWebSocket(new Request.Builder().url(mExternIp).build(),mWebSocketListener );
        }
    }

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Ready for review
     */
    @Override
    public void externalConnectionFailed() {
        callback.websocketConnectionFailed();
    }

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Ready for review
     */
    @Override
    public void receiveSockets(List<SocketDTO> sockets) {
        callback.receiveSockets(sockets);
    }

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Ready for review
     */
    @Override
    public void setIps(String internal, String external) {
        mInternIp = internal;
        mExternIp = external;
    }
}
