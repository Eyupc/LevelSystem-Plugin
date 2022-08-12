package com.master.websocket.communication.incoming.messages;

import com.master.websocket.Habbo.HabboClient;
import com.master.websocket.Habbo.HabboClientManager;
import com.master.websocket.communication.incoming.IncomingMessage;
import gnu.trove.map.hash.THashMap;
import io.netty.channel.Channel;

import java.sql.SQLException;

public class GoToRoomEvent extends IncomingMessage {
    public GoToRoomEvent(String header, THashMap<String, Object> data, Channel client) throws SQLException {
        super(header, data, client);
        this.execute();
    }

    @Override
    public void execute() throws SQLException {
        HabboClient client = HabboClientManager.getHabboClient(this.client);
        if(client != null)
            client.getHabbo().goToRoom((int)((double)data.get("room_id")));
    }
}
