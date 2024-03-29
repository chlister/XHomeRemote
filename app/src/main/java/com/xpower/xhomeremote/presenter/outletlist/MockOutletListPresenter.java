/**
 * Mockup OutletListPresenter
 *
 * @author Martin J. J.
 * @version 1.0
 * @since 11/20/2019
 */
package com.xpower.xhomeremote.presenter.outletlist;

import com.xpower.xhomeremote.data.model.HomeApplianceType;
import com.xpower.xhomeremote.data.model.Outlet;
import com.xpower.xhomeremote.ui.outletlist.IOutletListView;

import java.util.ArrayList;

public class MockOutletListPresenter implements IOutletListPresenter {
    private IOutletListView mView;

    /**
     * @author  Martin J. J.
     * @since   11/20/2019
     * @status  Under Development
     */
    public MockOutletListPresenter(IOutletListView view){
        mView = view;
    }

    /**
     * @author  Martin J. J.
     * @since   11/20/2019
     * @status  Under Development
     */
    @Override
    public void getOutlets() {
        ArrayList<Outlet> data = new ArrayList<>();
        data.add(new Outlet(0,0, "Name1", HomeApplianceType.getNonType(), false));
        data.add(new Outlet(1,0, "MY PC", HomeApplianceType.getType("laptop"), false));
        data.add(new Outlet(2,3, "Laptop", HomeApplianceType.getType("laptop"), false));

        mView.onOutletDataReceived(data);

    }

    /**
     * @author  Martin J. J.
     * @since   11/20/2019
     * @status  Under Development
     */
    @Override
    public void changeState(Outlet outlet, boolean isChecked){
        int i = 1;
    }

}
