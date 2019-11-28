/**
 * TODO: Add class description
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
     * @version 1.0
     * @since   11/20/2019
     * @status  Under Development
     */
    public MockOutletListPresenter(IOutletListView view){
        mView = view;
    }

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Under Development
     */
    @Override
    public void getOutlets() {
        ArrayList<Outlet> data = new ArrayList<>();

        data.add(new Outlet(1, 1, "BathRoomLight", HomeApplianceType.LIGHT));
        data.add(new Outlet(1, 2, "OtherBathLight", HomeApplianceType.LIGHT));
        data.add(new Outlet(1, 2, "", HomeApplianceType.OTHER));
        data.add(new Outlet(2, 1, "Kitchen Coffee Machine", HomeApplianceType.COFFEE_MACHINE));
        data.add(new Outlet(2, 2, "", HomeApplianceType.LAPTOP));
        data.add(new Outlet(3, 1, "", HomeApplianceType.NON));
        data.add(new Outlet(3, 2, "Bedroom Coffee Machine", HomeApplianceType.COFFEE_MACHINE));
        data.add(new Outlet(4, 1, "", HomeApplianceType.NON));
        data.add(new Outlet(4, 2, "guest Bedroom Coffee Machine", HomeApplianceType.COFFEE_MACHINE));

        mView.updateOutletList(data);

    }
}
