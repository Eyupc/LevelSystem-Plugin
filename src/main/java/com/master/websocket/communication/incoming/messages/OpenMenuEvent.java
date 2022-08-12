package com.master.websocket.communication.incoming.messages;

import com.master.LevelSystem.LevelSystemManager;
import com.master.LevelSystem.Player.LevelPlayer;
import com.master.websocket.Habbo.HabboClient;
import com.master.websocket.Habbo.HabboClientManager;
import com.master.websocket.communication.incoming.IncomingMessage;
import com.master.websocket.communication.outgoing.messages.ShowMenuEvent;
import gnu.trove.map.hash.THashMap;
import io.netty.channel.Channel;

import java.sql.SQLException;

public class OpenMenuEvent extends IncomingMessage {
    public OpenMenuEvent(String header, THashMap<String,Object> data, Channel ws ) throws  SQLException {
        super(header,data,ws);
        this.execute();
    }

    @Override
    public void execute() throws SQLException {
        HabboClient client = HabboClientManager.getHabboClient(super.client);
        if(client!=null) {
            LevelPlayer player = LevelSystemManager.getOnlinePlayers().get(client.getHabbo().getHabboInfo().getId());
            if (player != null) {
                client.send(new ShowMenuEvent("ShowMenuEvent", player));
            }
        }


    }
}
