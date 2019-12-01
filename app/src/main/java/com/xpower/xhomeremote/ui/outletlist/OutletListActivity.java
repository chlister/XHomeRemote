/**
 * Activity for view the presents a list of outlets
 * @author  Martin J. J.
 * @version 1.0
 * @since   11/20/2019
 */
package com.xpower.xhomeremote.ui.outletlist;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xpower.xhomeremote.R;
import com.xpower.xhomeremote.data.model.Outlet;
import com.xpower.xhomeremote.presenter.outletlist.IOutletListPresenter;
import com.xpower.xhomeremote.presenter.outletlist.MockOutletListPresenter;
import com.xpower.xhomeremote.presenter.outletlist.OutletListPresenter;
import com.xpower.xhomeremote.ui.base.BaseActivity;
import com.xpower.xhomeremote.ui.outletregister.OutletRegisterActivity;

import java.util.List;

public class OutletListActivity extends BaseActivity implements IOutletListView, OutletViewAdapter.onClickListner {
    private RecyclerView mRecyclerView;
    private OutletViewAdapter mOutletViewAdapter;
    private IOutletListPresenter mOutletPresenter;
    private SearchView searchView;

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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //mOutletPresenter = new OutletListPresenter(this);
        mOutletPresenter = new MockOutletListPresenter(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.toolbar_title);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.outletlist_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mOutletViewAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mOutletViewAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
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
