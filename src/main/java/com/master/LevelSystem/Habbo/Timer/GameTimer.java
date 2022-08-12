package com.master.LevelSystem.Habbo.Timer;

import com.master.LevelSystem.LevelSystemManager;
import com.master.LevelSystem.Player.LevelPlayer;

import java.util.TimerTask;

public class GameTimer extends TimerTask {

    @Override
    public void run() {
        for(LevelPlayer player : LevelSystemManager.getOnlinePlayers().values()){
            if(player != null)
                player.getPlayerStats().addOnlineTime(1);
        }
    }
}
