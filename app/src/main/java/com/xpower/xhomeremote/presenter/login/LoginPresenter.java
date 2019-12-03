/**
 * @author Martin J. J.
 * @version 1.0
 * @since 11/26/2019
 */
package com.xpower.xhomeremote.presenter.login;

import com.xpower.xhomeremote.data.websocket.IWebSocketManager;
import com.xpower.xhomeremote.data.websocket.WebSocketManager;
import com.xpower.xhomeremote.ui.login.ILoginView;

public class LoginPresenter implements ILoginPresenter, ILoginPresenterCallback {
    private IWebSocketManager mWebSocketManager;
    private ILoginView mView;

    /**
     * @author  Martin J. J.
     * @since   11/26/2019
     * @status  Done
     */
    public LoginPresenter(ILoginView view, IWebSocketManager webSocketManager){
        mView = view;
        mWebSocketManager = webSocketManager;
        mWebSocketManager.setFailedCallback(this);
        mWebSocketManager.setSuccessCallback(this);
    }

    /**
     * @author  Martin J. J.
     * @since   11/26/2019
     * @status  Done
     */
    @Override
    public void login(String user, String password){
        mWebSocketManager.setIps(user, password);
        mWebSocketManager.startSocketConnection();
    }

    /**
     * @author  Martin J. J.
     * @since   11/26/2019
     * @status  Done
     */
    @Override
    public void onWebsocketConnectionSuccess() {
        mView.onConnectionSuccess();
    }

    /**
     * @author  Martin J. J.
     * @since   11/26/2019
     * @status  Done
     */
    @Override
    public void onWebsocketConnectionFailed(String msg) {
        mView.onConnectionFailed(msg);
    }
}
