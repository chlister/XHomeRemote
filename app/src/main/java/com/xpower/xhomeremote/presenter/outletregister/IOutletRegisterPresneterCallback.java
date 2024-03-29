/**
 *
 * @author Martin J. J.
 * @version 2.0
 * @since 11/25/2019
 */
package com.xpower.xhomeremote.presenter.outletregister;

import com.xpower.xhomeremote.data.websocket.callback.IWebsocketConnectionFailed;
import com.xpower.xhomeremote.data.websocket.callback.IWebsocketRegister;

public interface IOutletRegisterPresneterCallback extends IWebsocketRegister, IWebsocketConnectionFailed {
}
