/**
 * This class use allow is to inject the presenter to the activities
 *
 * @author Martin J. J.
 * @version 1.0
 * @since   12/7/2019
 */
package com.xpower.xhomeremote;

import android.app.Application;

import com.xpower.xhomeremote.data.websocket.WebSocketManager;
import com.xpower.xhomeremote.presenter.login.ILoginPresenter;
import com.xpower.xhomeremote.presenter.login.LoginPresenter;
import com.xpower.xhomeremote.presenter.outletlist.IOutletListPresenter;
import com.xpower.xhomeremote.presenter.outletlist.OutletListPresenter;
import com.xpower.xhomeremote.presenter.outletregister.IOutletRegisterPresenter;
import com.xpower.xhomeremote.presenter.outletregister.OutletRegisterPresenter;
import com.xpower.xhomeremote.ui.login.ILoginView;
import com.xpower.xhomeremote.ui.outletlist.IOutletListView;
import com.xpower.xhomeremote.ui.outletregister.IOutletRegisterView;

public class XHomeRemote extends Application {

    /**
     * This method inject the WebsocketManager and Activity (view) dependency for the login presenter
     * @author  Martin J. J.
     * @version 1.0
     * @since   12/7/2019
     * @status  Done
     */
    public ILoginPresenter getLoginPresenter(ILoginView view){
        return new LoginPresenter(view, WebSocketManager.getInstance());
    }

    /**
     * This method inject the WebsocketManager and Activity (view) dependency for the OutletList presenter
     * @author  Martin J. J.
     * @version 1.0
     * @since   12/7/2019
     * @status  Done
     */
    public IOutletListPresenter getOutletListPresenter(IOutletListView view){
        return new OutletListPresenter(view, WebSocketManager.getInstance());
    }

    /**
     * This method inject the WebsocketManager and Activity (view) dependency for the getOutletRegisterPresenter presenter
     * @author  Martin J. J.
     * @version 1.0
     * @since   12/7/2019
     * @status  Done
     */
    public IOutletRegisterPresenter getOutletRegisterPresenter(IOutletRegisterView view){
        return new OutletRegisterPresenter(view, WebSocketManager.getInstance());
    }

}
