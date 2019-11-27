/**
 * Activity for view the presents a list of sockets
 * @author  Martin J. J.
 * @version 1.0
 * @since   11/20/2019
 */
package com.xpower.xhomeremote.ui.socketlist;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xpower.xhomeremote.R;
import com.xpower.xhomeremote.data.model.SocketDTO;
import com.xpower.xhomeremote.presenter.socketlist.ISocketPresenter;
import com.xpower.xhomeremote.presenter.socketlist.MockSocketPresenter;
import com.xpower.xhomeremote.presenter.socketlist.SocketPresenter;
import com.xpower.xhomeremote.ui.base.BaseActivity;
import com.xpower.xhomeremote.ui.socketregister.SocketRegisterActivity;

import java.util.List;

public class SocketListActivity extends BaseActivity implements ISocketListView, SocketViewAdapter.onClickListner {
    private RecyclerView mRecyclerView;
    private SocketViewAdapter mSocketViewAdapter;
    private ISocketPresenter mSocketPresenter;

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Under Development
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_list);
        //mSocketPresenter = new SocketPresenter(this);
        mSocketPresenter = new MockSocketPresenter(this);

        mRecyclerView = findViewById(R.id.SocketListActivity_recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mSocketViewAdapter = new SocketViewAdapter(this);
        mSocketViewAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mSocketViewAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSocketPresenter.getSockets();
    }

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Under Development
     */
    @Override
    public void updateSocketList(List<SocketDTO> sockets) {
        mSocketViewAdapter.setData(sockets);
    }


    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Under Development
     */
    @Override
    public void ConnectionFeedback(final boolean b) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(b) {
                    showMessage("Connection OK");
                    mSocketPresenter.getSockets();
                }
                else
                    showMessage("Connection Failed");
            }
        });
    }

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Defined
     */
    @Override
    public void onItemClick(SocketDTO item) {
        //TODO: turn on/off socket
    }

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Under Development
     */
    @Override
    public void onItemLongClick(SocketDTO item) {
        Intent i = new Intent(this, SocketRegisterActivity.class);
        i.putExtra(SocketRegisterActivity.DATA_INTENT_SOCKET, item);
        startActivity(i);
    }
}
