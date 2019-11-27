/**
 * TODO: Add class description
 *
 * @author Martin J. J.
 * @version 1.0
 * @since 11/20/2019
 */
package com.xpower.xhomeremote.presenter.socketlist;

import com.xpower.xhomeremote.data.model.HomeApplianceType;
import com.xpower.xhomeremote.data.model.SocketDTO;
import com.xpower.xhomeremote.ui.socketlist.ISocketListView;

import java.util.ArrayList;

public class MockSocketPresenter implements ISocketPresenter {
    private ISocketListView mView;

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Under Development
     */
    public MockSocketPresenter(ISocketListView view){
        mView = view;
    }

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Under Development
     */
    @Override
    public void getSockets() {
        ArrayList<SocketDTO> data = new ArrayList<>();

        data.add(new SocketDTO(1, 1, "BathRoomLight", HomeApplianceType.LIGHT));
        data.add(new SocketDTO(1, 2, "OtherBathLight", HomeApplianceType.LIGHT));
        data.add(new SocketDTO(1, 2, "", HomeApplianceType.OTHER));
        data.add(new SocketDTO(2, 1, "Kitchen Coffee Machine", HomeApplianceType.COFFEE_MACHINE));
        data.add(new SocketDTO(2, 2, "", HomeApplianceType.LAPTOP));
        data.add(new SocketDTO(3, 1, "", HomeApplianceType.NON));
        data.add(new SocketDTO(3, 2, "Bedroom Coffee Machine", HomeApplianceType.COFFEE_MACHINE));
        data.add(new SocketDTO(4, 1, "", HomeApplianceType.NON));
        data.add(new SocketDTO(4, 2, "guest Bedroom Coffee Machine", HomeApplianceType.COFFEE_MACHINE));

        mView.updateSocketList(data);

    }
}
