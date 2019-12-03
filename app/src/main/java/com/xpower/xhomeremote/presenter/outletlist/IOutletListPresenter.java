/**
 * @author Martin J. J.
 * @version 2.0
 * @since 11/25/2019
 */
package com.xpower.xhomeremote.presenter.outletlist;

import com.xpower.xhomeremote.data.model.Outlet;

public interface IOutletListPresenter {
    void getOutlets();
    void changeState(Outlet outlet, boolean isChecked);
}
