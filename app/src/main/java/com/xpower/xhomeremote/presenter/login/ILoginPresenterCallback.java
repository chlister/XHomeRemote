/**
 * @author Martin J. J.
 * @version 1.0
 * @since 11/26/2019
 */
package com.xpower.xhomeremote.presenter.login;

import com.xpower.xhomeremote.data.websocket.callback.IWebsocketConnectionFailed;
import com.xpower.xhomeremote.data.websocket.callback.IWebsocketConnectionSuccess;

public interface ILoginPresenterCallback extends IWebsocketConnectionSuccess, IWebsocketConnectionFailed {
}
