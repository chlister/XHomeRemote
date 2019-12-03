/**
 * @author Martin J. J.
 * @version 1.0
 * @since 11/26/2019
 */
package com.xpower.xhomeremote.data.websocket.callback;

public interface IWebsocketRegister {
    void onRegisterSuccess();
    void onRegisterFailed(String msg);
}
