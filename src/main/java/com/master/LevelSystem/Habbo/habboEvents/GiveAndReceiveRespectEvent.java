package com.master.LevelSystem.Habbo.habboEvents;

import com.eu.habbo.plugin.EventHandler;
import com.eu.habbo.plugin.EventListener;
import com.eu.habbo.plugin.events.users.UserRespectedEvent;
import com.master.LevelSystem.LevelSystemManager;
import com.master.LevelSystem.Player.LevelPlayer;

public class GiveAndReceiveRespectEvent implements EventListener {

    @EventHandler
    public static void giveRespect(UserRespectedEvent e) throws NullPointerException{
        LevelPlayer Tplayer = LevelSystemManager.getOnlinePlayers().get(e.habbo.getHabboInfo().getId());
        LevelPlayer sender = LevelSystemManager.getOnlinePlayers().get(e.from.getHabboInfo().getId());
        if(Tplayer != null)
        Tplayer.getPlayerStats().addRespect(1);
        if(sender != null)
        sender.getPlayerStats().addRespectsGiven(1);

    }
}
