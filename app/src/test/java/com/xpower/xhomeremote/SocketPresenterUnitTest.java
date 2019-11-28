package com.xpower.xhomeremote;

import com.xpower.xhomeremote.data.model.HomeApplianceType;
import com.xpower.xhomeremote.data.model.Socket;
import com.xpower.xhomeremote.presenter.socketlist.ISocketPresenter;
import com.xpower.xhomeremote.presenter.socketlist.SocketPresenter;
import com.xpower.xhomeremote.ui.socketlist.ISocketListView;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SocketPresenterUnitTest {

    @Test
    public void getSocketsTest() {
        ISocketPresenter presenter = new SocketPresenter(new ISocketListView() {
            @Override
            public void updateSocketList(List<Socket> sockets) {
                assertEquals(7, sockets.size());
                assertEquals(new Socket(1, 2, "OtherBathLight", HomeApplianceType.LIGHT).toString(), sockets.get(1).toString());
            }
        });
        presenter.getSockets();
    }

}
