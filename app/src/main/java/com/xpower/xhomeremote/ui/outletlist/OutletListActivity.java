/**
 * Activity for view that presents a list of outlets
 * @author  Martin J. J.
 * @version 2.0
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
import com.xpower.xhomeremote.XHomeRemote;
import com.xpower.xhomeremote.data.model.Outlet;
import com.xpower.xhomeremote.presenter.outletlist.IOutletListPresenter;
import com.xpower.xhomeremote.ui.base.BaseActivity;
import com.xpower.xhomeremote.ui.outletregister.OutletRegisterActivity;

import java.util.List;

public class OutletListActivity extends BaseActivity implements IOutletListView, OutletViewAdapter.ICardClickListner {
    private RecyclerView mRecyclerView;
    private OutletViewAdapter mOutletViewAdapter;
    private IOutletListPresenter mPresenter;
    private SearchView mSearchView;

    /**
     * @author  Martin J. J.
     * @since   11/20/2019
     * @status  Done
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outlet_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.toolbar_title);

        mRecyclerView = findViewById(R.id.OutletListActivity_recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mOutletViewAdapter = new OutletViewAdapter(this);
        mOutletViewAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mOutletViewAdapter);
    }

    /**
     * We inject the callback onResume to insure that the active Presenter/Activity gets the callback
     * @author  Martin J. J.
     * @since   11/20/2019
     * @status  Done
     */
    @Override
    protected void onResume() {
        super.onResume();
        mPresenter = ((XHomeRemote)getApplication()).getOutletListPresenter(this);
        mPresenter.getOutlets();
    }

    /**
     * This method specify the options for the activites menu (topbar)
     * @author  Martin J. J.
     * @since   11/28/2019
     * @status  Done
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.outletlist_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        mSearchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        mSearchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
     * Filter received outlets according to the Search query
     * @author  Martin J. J.
     * @since   11/20/2019
     * @status  Done
     */
    @Override
    public void onOutletDataReceived(List<Outlet> outlets) {
        runOnUiThread(() -> {
            mOutletViewAdapter.setData(outlets);
            if(mSearchView != null)
                mOutletViewAdapter.getFilter().filter(mSearchView.getQuery());
        });
    }


    /**
     * Listener for when a card has been pressed
     * @author  Martin J. J.
     * @since   11/20/2019
     * @status  Done
     */
    @Override
    public void onItemClick(Outlet item) {
        showMessage(this.getString(R.string.outletlist_onitemclick_message));
    }

    /**
     * Listener for when a card has been long pressed
     * @author  Martin J. J.
     * @since   11/20/2019
     * @status  Done
     */
    @Override
    public void onItemLongClick(Outlet item) {
        Intent i = new Intent(this, OutletRegisterActivity.class);
        i.putExtra(OutletRegisterActivity.DATA_INTENT_OUTLET, item);
        startActivity(i);
    }

    /**
     * Listener for when the switch on a card change state
     * @author  Martin J. J.
     * @since   11/20/2019
     * @status  Done
     */
    @Override
    public void onChangeListener(Outlet item, boolean isChecked) {
        mPresenter.changeState(item, isChecked);
    }


}
