/**
 * TODO: Add class description
 *
 * @author Martin J. J.
 * @version 1.0
 * @since 11/20/2019
 */
package com.xpower.xhomeremote.ui.base;

import androidx.annotation.StringRes;

public interface IBaseActivity {
    void showLoading();

    void hideLoading();

    void showMessage(String message);

    void showMessage(@StringRes int resId);
}
