package com.master.LevelSystem.Habbo.habboEvents;

import com.eu.habbo.plugin.EventHandler;
import com.eu.habbo.plugin.EventListener;
import com.eu.habbo.plugin.events.users.UserLoginEvent;
import com.master.LevelSystem.LevelSystemManager;
import com.master.LevelSystem.Player.LevelPlayer;
import com.master.websocket.Habbo.HabboClient;
import com.master.websocket.Habbo.HabboClientManager;
import com.master.websocket.Habbo.SSOClientManager;
import com.master.websocket.communication.outgoing.messages.ShowMenuIconEvent;

import java.sql.SQLException;

public class OnLogin implements EventListener {
    @EventHandler
    public static void onLogin(UserLoginEvent event) throws SQLException {
        if (SSOClientManager.SSOclients.get(event.habbo.getHabboInfo().getSso()) != null) {
            new HabboClientManager().addClient(SSOClientManager.SSOclients.get(event.habbo.getHabboInfo().getSso()), event.habbo);
            SSOClientManager.SSOclients.remove(event.habbo.getHabboInfo().getSso());
        }

        HabboClient client = HabboClientManager.getHabboClient(event.habbo);
        LevelPlayer player = LevelPlayer.getLevelPlayer(event.habbo.getHabboInfo().getId());
        if(player==null){
        LevelPlayer.createLevelPlayer(event.habbo.getHabboInfo().getId());
        LevelSystemManager.addOnlinePlayer(LevelPlayer.getLevelPlayer(event.habbo.getHabboInfo().getId()));
        }else{
            LevelSystemManager.addOnlinePlayer(player);
        }
        if(client != null)
            client.send(new ShowMenuIconEvent("ShowMenuIconEvent",true));
    }
}
