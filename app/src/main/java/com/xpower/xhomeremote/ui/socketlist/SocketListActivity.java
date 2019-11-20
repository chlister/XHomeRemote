/**
 * Activity for view the presents a list of sockets
 * @author  Martin J. J.
 * @version 1.0
 * @since   11/20/2019
 */
package com.xpower.xhomeremote.ui.socketlist;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xpower.xhomeremote.R;
import com.xpower.xhomeremote.data.model.SocketDTO;
import com.xpower.xhomeremote.presenter.ISocketPresenter;
import com.xpower.xhomeremote.presenter.MockSocketPresenter;
import com.xpower.xhomeremote.ui.base.BaseActivity;

import java.util.List;

public class SocketListActivity extends BaseActivity implements ISocketListView {
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
        mSocketPresenter = new MockSocketPresenter(this);

        mRecyclerView = findViewById(R.id.SocketListActivity_recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mSocketViewAdapter = new SocketViewAdapter(this);
        mRecyclerView.setAdapter(mSocketViewAdapter);

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
}
