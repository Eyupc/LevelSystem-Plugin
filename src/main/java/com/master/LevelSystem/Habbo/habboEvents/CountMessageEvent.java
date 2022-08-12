package com.master.LevelSystem.Habbo.habboEvents;

import com.eu.habbo.Emulator;
import com.eu.habbo.plugin.EventHandler;
import com.eu.habbo.plugin.EventListener;
import com.eu.habbo.plugin.events.users.UserTalkEvent;
import com.master.LevelSystem.LevelSystemManager;
import com.master.LevelSystem.Player.LevelPlayer;

public class CountMessageEvent implements EventListener {
    @EventHandler
    public static void CountMessage(UserTalkEvent event){
        int roomid = event.habbo.getHabboInfo().getCurrentRoom().getId();
        LevelPlayer player = LevelSystemManager.getOnlinePlayers().get(event.habbo.getHabboInfo().getId());
        if(roomid == Emulator.getTexts().getInt("levelsystem.roomId"))
            player.getPlayerStats().addMessagesAmount(1);

    }
}
