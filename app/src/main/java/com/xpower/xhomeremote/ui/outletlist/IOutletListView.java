/**
 * This is the callback interface that are injected to the presenter
 *
 * @author Martin J. J.
 * @version 1.0
 * @since 11/20/2019
 */
package com.xpower.xhomeremote.ui.outletlist;

import com.xpower.xhomeremote.data.model.Outlet;
import com.xpower.xhomeremote.ui.base.IBaseActivity;

import java.util.List;

public interface IOutletListView extends IBaseActivity {
    void onOutletDataReceived(List<Outlet> outlets);
    void onConnectionFailed(String msg);
}
