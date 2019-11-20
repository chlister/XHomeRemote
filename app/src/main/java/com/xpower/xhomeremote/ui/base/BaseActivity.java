/**
 * BaseActivity is an expansion to the native AppCompatActivity. This allow us to reuse method that are assume to be used by multiple activities.
 *
 * @author Martin J. J.
 * @version 1.0
 * @since 11/20/2019
 */
package com.xpower.xhomeremote.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.xpower.xhomeremote.R;

public abstract class BaseActivity extends AppCompatActivity implements IBaseActivity {
    private ProgressDialog mProgress;

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Ready for review
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgress = new ProgressDialog(this);
    }

    /**
     * Shows the ProgressDialog for the user
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Ready for review
     */
    @Override
    public void showLoading() {
        hideLoading();
        mProgress.setMessage(getResources().getString(R.string.please_wait));
        mProgress.setCanceledOnTouchOutside(false);
        mProgress.setCancelable(false);
        mProgress.show();
    }

    /**
     * Hides the ProgressDialog for the user
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Ready for review
     */
    @Override
    public void hideLoading() {
        if (mProgress != null && mProgress.isShowing()) {
            mProgress.dismiss();
        }
    }

    /**
     * Shows a toast with the param message
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Ready for review
     */
    @Override
    public void showMessage(String message) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Shows a toast with the string from param ressource id.
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Ready for review
     */
    @Override
    public void showMessage(@StringRes int resId) {
        showMessage(getString(resId));
    }

}
