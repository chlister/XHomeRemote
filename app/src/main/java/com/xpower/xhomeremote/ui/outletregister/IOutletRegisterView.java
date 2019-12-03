/**
 * This is the callback interface that the OutletRegister activity.
 * @author  Martin J. J.
 * @version 1.0
 * @since   11/20/2019
 */
package com.xpower.xhomeremote.ui.outletregister;

import com.xpower.xhomeremote.ui.base.IBaseActivity;

public interface IOutletRegisterView extends IBaseActivity {
    void onRegisterSuccess();

    void onRegisterFailed();
}
