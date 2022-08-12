package com.master.LevelSystem.Habbo.habboEvents;

import com.eu.habbo.plugin.EventHandler;
import com.eu.habbo.plugin.EventListener;
import com.eu.habbo.plugin.events.users.UserDisconnectEvent;
import com.master.LevelSystem.LevelSystemManager;
import com.master.LevelSystem.Player.LevelPlayer;

import java.sql.SQLException;

public class OnLogout implements EventListener {
    @EventHandler
    public static void onLogout(UserDisconnectEvent event) throws SQLException {
        LevelPlayer player = LevelSystemManager.getOnlinePlayers().get(event.habbo.getHabboInfo().getId());
        if(player != null) {
            if(player.getPlayerInfo() != null)
                player.getPlayerInfo().update();
                player.getPlayerStats().update();
        }
        LevelSystemManager.removeOnlinePlayer(event.habbo.getHabboInfo().getId());
    }
}
