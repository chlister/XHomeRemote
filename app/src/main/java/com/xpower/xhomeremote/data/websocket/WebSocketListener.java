/**
 * TODO: Add class description
 *
 * @author Martin J. J.
 * @version 1.0
 * @since 11/20/2019
 */
package com.xpower.xhomeremote.data.websocket;

import android.util.Log;

import com.google.gson.internal.LinkedTreeMap;
import com.xpower.message.Message;
import com.xpower.message.MethodCode;
import com.xpower.message.model.SocketDTO;

import java.util.ArrayList;

import okhttp3.Response;
import okhttp3.WebSocket;

import static android.content.ContentValues.TAG;

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
        Log.i(TAG, "onOpen: " + response.message());
        callback.connectEstablis(webSocket);
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
        Log.i(TAG, "onMessage: " + text);
        Message m = new Message(text);
        if(m.getMethodCode().equals(MethodCode.GET_SOCKETS))
            callback.receiveSockets(SocketDTO.deserialize((ArrayList<LinkedTreeMap>) m.getObj()));
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
