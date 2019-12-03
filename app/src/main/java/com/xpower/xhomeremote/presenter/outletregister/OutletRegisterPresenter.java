/**
 * Presenter for the OutletRegister
 *
 * @author Martin J. J.
 * @version 1.0
 * @since 11/21/2019
 */
package com.xpower.xhomeremote.presenter.outletregister;

import com.xpower.xhomeremote.data.model.Outlet;
import com.xpower.xhomeremote.data.websocket.IWebSocketManager;
import com.xpower.xhomeremote.data.websocket.WebSocketManager;
import com.xpower.xhomeremote.ui.outletregister.IOutletRegisterView;

public class OutletRegisterPresenter implements IOutletRegisterPresneterCallback, IOutletRegisterPresenter {
    private IOutletRegisterView mView;
    private IWebSocketManager mWebSocketManager;

    /**
     * @author  Martin J. J.
     * @since   11/21/2019
     * @status  Done
     */
    public OutletRegisterPresenter(IOutletRegisterView mView, IWebSocketManager webSocketManager) {
        this.mView = mView;
        mWebSocketManager = webSocketManager;
        mWebSocketManager.setRegisterCallback(this);
    }

    /**
     * @author  Martin J. J.
     * @since   11/21/2019
     * @status  Done
     */
    @Override
    public void registerOutlet(Outlet outlet){
        mWebSocketManager.registerOutlet(outlet);
    }

    /**
     * @author  Martin J. J.
     * @since   11/21/2019
     * @status  Done
     */
    @Override
    public void onRegisterSuccess() {
        mView.onRegisterSuccess();
    }

    /**
     * @author  Martin J. J.
     * @since   11/21/2019
     * @status  Done
     */
    @Override
    public void onRegisterFailed(String msg) {
        mView.onRegisterFailed();
    }

    /**
     * @author  Martin J. J.
     * @since   11/21/2019
     * @status  Done
     */
    @Override
    public void onWebsocketConnectionFailed(String msg) {
        mView.onConnectionFailed(msg);
    }
}
