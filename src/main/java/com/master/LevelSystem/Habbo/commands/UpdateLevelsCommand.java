package com.master.LevelSystem.Habbo.commands;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.commands.Command;
import com.eu.habbo.habbohotel.gameclients.GameClient;
import com.master.LevelSystem.LevelSystemManager;

public class UpdateLevelsCommand extends Command {
    public UpdateLevelsCommand(String permission, String[] keys) {
        super(permission, keys);
    }

    @Override
    public boolean handle(GameClient gameClient, String[] strings) throws Exception {
        LevelSystemManager.getLevelManager().updateLevels();
        gameClient.getHabbo().whisper(Emulator.getTexts()
            .getValue("commands.succes.cmd_update_levels","Levels are successfully reloaded!"));
        return true;
    }
}
