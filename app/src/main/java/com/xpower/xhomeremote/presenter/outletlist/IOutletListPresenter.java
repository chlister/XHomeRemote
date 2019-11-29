/**
 * TODO: Add class description
 *
 * @author Martin J. J.
 * @version 1.0
 * @since 11/20/2019
 */
package com.xpower.xhomeremote.presenter.outletlist;

import com.xpower.xhomeremote.data.model.Outlet;

public interface IOutletListPresenter {
    void getOutlets();
    void changeState(Outlet outlet, boolean isChecked);
}
