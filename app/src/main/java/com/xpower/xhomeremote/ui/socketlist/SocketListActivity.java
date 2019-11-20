/**
 * Activity for view the presents a list of sockets
 * @author  Martin J. J.
 * @version 1.0
 * @since   11/20/2019
 */
package com.xpower.xhomeremote.ui.socketlist;

import android.os.Bundle;

import com.xpower.xhomeremote.R;
import com.xpower.xhomeremote.ui.base.BaseActivity;

public class SocketListActivity extends BaseActivity {

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Ready for review
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_list);
    }
}
