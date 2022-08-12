package com.master.LevelSystem.Habbo.commands;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.commands.Command;
import com.eu.habbo.habbohotel.gameclients.GameClient;
import com.master.LevelSystem.LevelSystemManager;
import com.master.LevelSystem.Player.LevelPlayer;

public class UpdateTasksCommand extends Command {
    public UpdateTasksCommand(String permission, String[] keys) {
        super(permission, keys);
    }

    @Override
    public boolean handle(GameClient gameClient, String[] strings) throws Exception {
        LevelSystemManager.getTaskManager().updateTasks();
        for(LevelPlayer player : LevelSystemManager.getOnlinePlayers().values()){
            player.updateTasks();
        }
        gameClient.getHabbo().whisper(Emulator.getTexts()
            .getValue("commands.succes.cmd_update_tasks","Tasks are successfully reloaded!"));
        return true;
    }
}
