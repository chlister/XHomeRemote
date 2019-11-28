package com.xpower.xhomeremote;

import com.xpower.xhomeremote.data.model.HomeApplianceType;
import com.xpower.xhomeremote.data.model.Outlet;
import com.xpower.xhomeremote.presenter.outletlist.IOutletListPresenter;
import com.xpower.xhomeremote.presenter.outletlist.OutletListPresenter;
import com.xpower.xhomeremote.ui.outletlist.IOutletListView;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class OutletListPresenterUnitTest {

    @Test
    public void getOutletsTest() {
        IOutletListPresenter presenter = new OutletListPresenter(new IOutletListView() {
            @Override
            public void updateOutletList(List<Outlet> outlets) {
                assertEquals(7, outlets.size());
                assertEquals(new Outlet(1, 2, "OtherBathLight", HomeApplianceType.LIGHT).toString(), outlets.get(1).toString());
            }

            @Override
            public void ConnectionFeedback(boolean b) {

            }
        });
        presenter.getOutlets();
    }

}
