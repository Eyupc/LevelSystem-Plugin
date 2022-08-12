package com.master.websocket.communication.incoming.messages;

import com.master.LevelSystem.LevelSystemManager;
import com.master.LevelSystem.Player.LevelPlayer;
import com.master.websocket.Habbo.HabboClient;
import com.master.websocket.Habbo.HabboClientManager;
import com.master.websocket.communication.incoming.IncomingMessage;
import com.master.websocket.communication.outgoing.messages.ShowActionEvent;
import gnu.trove.map.hash.THashMap;
import io.netty.channel.Channel;

import java.sql.SQLException;

public class RequestActionEvent extends IncomingMessage {

    public RequestActionEvent(String header, THashMap<String,Object> data, Channel client) throws SQLException {
        super(header, data, client);
        this.execute();
    }

    @Override
    public void execute() throws SQLException {
        HabboClient client = HabboClientManager.getHabboClient(this.client);
        if (client != null) {
            LevelPlayer player = LevelSystemManager.getOnlinePlayers().get(client.getHabbo().getHabboInfo().getId());
            client.send(new ShowActionEvent(player));
        }
    }
}
