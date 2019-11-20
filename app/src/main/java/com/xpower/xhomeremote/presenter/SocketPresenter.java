/**
 * TODO: Add class description
 *
 * @author Martin J. J.
 * @version 1.0
 * @since 11/20/2019
 */
package com.xpower.xhomeremote.presenter;

import com.xpower.xhomeremote.data.DataManager;
import com.xpower.xhomeremote.data.model.SocketDTO;
import com.xpower.xhomeremote.data.socket.IWebSocketCallback;
import com.xpower.xhomeremote.ui.socketlist.ISocketListView;

import java.util.List;

public class SocketPresenter implements ISocketPresenter, ISocketPresenterCallback {
    private ISocketListView mView;
    private DataManager mDataManager;

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Under Development
     */
    public SocketPresenter(ISocketListView view){
        mView = view;
        mDataManager = DataManager.getInstance(this);
        mDataManager.startSocketConnection();
    }

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Defined
     */
    @Override
    public void getSockets() {

    }

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Ready for Review
     */
    @Override
    public void receiveSockets(List<SocketDTO> sockets) {
        mView.updateSocketList(sockets);
    }

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Defined
     */
    @Override
    public void websocketConnectionFailed() {

    }
}
