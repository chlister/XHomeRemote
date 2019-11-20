/**
 * TODO: Add class description
 *
 * @author Martin J. J.
 * @version 1.0
 * @since 11/20/2019
 */
package com.xpower.xhomeremote.data.socket;

import okhttp3.Response;
import okhttp3.WebSocket;

public final class WebSocketListener extends okhttp3.WebSocketListener {
    private IWebSocketCallback callback;

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Defined
     */
    public WebSocketListener(IWebSocketCallback callback){
        this.callback = callback;
    }

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Defined
     */
    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
    }

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Defined
     */
    @Override
    public void onMessage(WebSocket webSocket, String text) {
        super.onMessage(webSocket, text);
    }

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Defined
     */
    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        super.onClosing(webSocket, code, reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        super.onFailure(webSocket, t, response);
    }
}
