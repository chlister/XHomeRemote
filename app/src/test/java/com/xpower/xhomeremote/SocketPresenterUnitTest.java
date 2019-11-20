package com.xpower.xhomeremote;

import com.xpower.xhomeremote.data.model.HomeApplianceType;
import com.xpower.xhomeremote.data.model.SocketDTO;
import com.xpower.xhomeremote.presenter.ISocketPresenter;
import com.xpower.xhomeremote.presenter.SocketPresenter;
import com.xpower.xhomeremote.ui.socketlist.ISocketListView;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SocketPresenterUnitTest {

    @Test
    public void getSocketsTest() {
        ISocketPresenter presenter = new SocketPresenter(new ISocketListView() {
            @Override
            public void updateSocketList(List<SocketDTO> sockets) {
                assertEquals(7, sockets.size());
                assertEquals(new SocketDTO(1, 2, "OtherBathLight", HomeApplianceType.LIGHT).toString(), sockets.get(1).toString());
            }
        });
        presenter.getSockets();
    }

}
