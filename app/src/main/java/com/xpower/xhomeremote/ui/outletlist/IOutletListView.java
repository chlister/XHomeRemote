/**
 * This is the callback interface that are injected to the presenter
 *
 * @author Martin J. J.
 * @version 1.0
 * @since 11/20/2019
 */
package com.xpower.xhomeremote.ui.outletlist;

import com.xpower.xhomeremote.data.model.Outlet;

import java.util.List;

public interface IOutletListView {
    void updateOutletList(List<Outlet> outlets);
    void ConnectionFeedback(boolean b);
}
