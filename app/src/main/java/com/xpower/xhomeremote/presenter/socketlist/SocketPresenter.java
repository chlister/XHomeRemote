/**
 * TODO: Add class description
 *
 * @author Martin J. J.
 * @version 1.0
 * @since 11/20/2019
 */
package com.xpower.xhomeremote.presenter.socketlist;

import com.xpower.xhomeremote.data.model.Socket;
import com.xpower.xhomeremote.data.websocket.IWebSocketManager;
import com.xpower.xhomeremote.data.websocket.WebSocketManager;
import com.xpower.xhomeremote.ui.socketlist.ISocketListView;

import java.util.List;

public class SocketPresenter implements ISocketPresenter, ISocketPresenterCallback {
    private ISocketListView mView;
    private IWebSocketManager mWebSocketManager;

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Ready for review
     */
    public SocketPresenter(ISocketListView view){
        mView = view;
        mWebSocketManager = WebSocketManager.getInstance();
        mWebSocketManager.setSuccesCallback(this);
        mWebSocketManager.setFailedCallback(this);
        mWebSocketManager.setReceiveSocketCallback(this);
        mWebSocketManager.startSocketConnection();
    }

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Defined
     */
    @Override
    public void getSockets() {
        mWebSocketManager.getSockets();
    }

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Ready for Review
     */
    @Override
    public void receiveSockets(List<Socket> sockets) {
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
        mView.ConnectionFeedback(false);
    }

    @Override
    public void websocketConnectionSucces() {
        mView.ConnectionFeedback(true);
    }
}
