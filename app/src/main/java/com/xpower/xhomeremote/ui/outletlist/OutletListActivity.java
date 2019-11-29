/**
 * Activity for view the presents a list of outlets
 * @author  Martin J. J.
 * @version 1.0
 * @since   11/20/2019
 */
package com.xpower.xhomeremote.ui.outletlist;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xpower.xhomeremote.R;
import com.xpower.xhomeremote.data.model.Outlet;
import com.xpower.xhomeremote.presenter.outletlist.IOutletListPresenter;
import com.xpower.xhomeremote.presenter.outletlist.OutletListPresenter;
import com.xpower.xhomeremote.ui.base.BaseActivity;
import com.xpower.xhomeremote.ui.outletregister.OutletRegisterActivity;

import java.util.List;

public class OutletListActivity extends BaseActivity implements IOutletListView, OutletViewAdapter.onClickListner {
    private RecyclerView mRecyclerView;
    private OutletViewAdapter mOutletViewAdapter;
    private IOutletListPresenter mOutletPresenter;

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Under Development
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outlet_list);
        mOutletPresenter = new OutletListPresenter(this);
        //mOutletPresenter = new MockOutletListPresenter(this);

        mRecyclerView = findViewById(R.id.OutletListActivity_recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mOutletViewAdapter = new OutletViewAdapter(this);
        mOutletViewAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mOutletViewAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mOutletPresenter.getOutlets();
    }

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Under Development
     */
    @Override
    public void updateOutletList(List<Outlet> outlets) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mOutletViewAdapter.setData(outlets);
                mOutletViewAdapter.notifyDataSetChanged();
            }
        });
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
                    mOutletPresenter.getOutlets();
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
    public void onItemClick(Outlet item) {
        showMessage("Hold nede på kort for a registrere outlet");
    }

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Under Development
     */
    @Override
    public void onItemLongClick(Outlet item) {
        Intent i = new Intent(this, OutletRegisterActivity.class);
        i.putExtra(OutletRegisterActivity.DATA_INTENT_OUTLET, item);
        startActivity(i);
    }

    @Override
    public void onChangeListener(Outlet item, boolean isChecked) {
        mOutletPresenter.changeState(item, isChecked);
    }
}
