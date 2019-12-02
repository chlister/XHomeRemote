/**
 * TODO: Add class description
 *
 * @author Martin J. J.
 * @version 1.0
 * @since 11/20/2019
 */
package com.xpower.xhomeremote.presenter.outletlist;

import com.xpower.xhomeremote.data.model.Outlet;
import com.xpower.xhomeremote.data.websocket.IWebSocketManager;
import com.xpower.xhomeremote.data.websocket.WebSocketManager;
import com.xpower.xhomeremote.ui.outletlist.IOutletListView;

import java.util.List;

public class OutletListPresenter implements IOutletListPresenter, IOutletListPresenterCallback {
    private IOutletListView mView;
    private IWebSocketManager mWebSocketManager;

    /**
     * @author  Martin J. J.
     * @since   11/20/2019
     * @status  Ready for review
     */
    public OutletListPresenter(IOutletListView view){
        mView = view;
        mWebSocketManager = WebSocketManager.getInstance();
        mWebSocketManager.setFailedCallback(this);
        mWebSocketManager.setReceiveOutletCallback(this);
    }

    /**
     * @author  Martin J. J.
     * @since   11/20/2019
     * @status  Defined
     */
    @Override
    public void getOutlets() {
        mWebSocketManager.getOutlets();
    }

    /**
     * @author  Martin J. J.
     * @since   11/20/2019
     * @status  Defined
     */
    @Override
    public void changeState(Outlet outlet, boolean isChecked) {
        outlet.state = isChecked;
        mWebSocketManager.updateOutlet(outlet);
    }


    /**
     * @author  Martin J. J.
     * @since   11/20/2019
     * @status  Ready for Review
     */
    @Override
    public void onReceiveOutlet(List<Outlet> outlets) {
        mView.onOutletDataReceived(outlets);
    }

    /**
     * @author  Martin J. J.
     * @since   11/20/2019
     * @status  Defined
     */
    @Override
    public void onWebsocketConnectionFailed(String msg) {
        mView.onConnectionFailed(msg);
    }
}
