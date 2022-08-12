package com.master.LevelSystem.Habbo;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.commands.CommandHandler;
import com.master.LevelSystem.Habbo.commands.UpdateLevelsCommand;
import com.master.LevelSystem.Habbo.commands.UpdateTasksCommand;
import com.master.LevelSystem.Habbo.habboEvents.*;
import com.master.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterUtils {
    public RegisterUtils(){

    }
    public void Init(){
        this.registerEvents();
        this.addCommands();
        this.registerCommands();
        this.registerTexts();
    }

    private void registerEvents(){
        Emulator.getPluginManager().registerEvents(Main.INSTANCE,new OnLogin());
        Emulator.getPluginManager().registerEvents(Main.INSTANCE,new OnLogout());
        Emulator.getPluginManager().registerEvents(Main.INSTANCE,new StepOnTaskTileEvent());
        Emulator.getPluginManager().registerEvents(Main.INSTANCE,new CountMessageEvent());
        Emulator.getPluginManager().registerEvents(Main.INSTANCE,new GiveAndReceiveRespectEvent());

    }


    private void registerTexts(){
        Emulator.getTexts().register("commands.keys.cmd_update_levels","update_levels");
        Emulator.getTexts().register("commands.keys.cmd_update_tasks","update_tasks");

        Emulator.getTexts().register("commands.succes.cmd_update_levels","Levels are successfully reloaded!");
        Emulator.getTexts().register("commands.succes.cmd_update_tasks","Tasks are successfully reloaded!");

        Emulator.getTexts().register("levelsystem.task_done","Tebrikler, Görev başarıyla tamamlandı!  50 XP kazandın.");
        Emulator.getTexts().register("levelsystem.roomId","361");
    }

    private void addCommands(){
        CommandHandler.addCommand(new UpdateLevelsCommand("cmd_update_levels",Emulator.getTexts().getValue("commands.keys.cmd_update_levels").split(";")));
        CommandHandler.addCommand(new UpdateTasksCommand("cmd_update_tasks",Emulator.getTexts().getValue("commands.keys.cmd_update_tasks").split(";")));
        this.registerCommands();
    }

    private void registerCommands(){
        try {
            boolean reloadPermissions = false;
            reloadPermissions = registerPermission("cmd_update_levels", "'0', '1'", "0", reloadPermissions);
            reloadPermissions = registerPermission("cmd_update_tasks", "'0', '1'", "0", reloadPermissions);

            if (reloadPermissions)
            {
                Emulator.getGameEnvironment().getPermissionsManager().reload();
            }
        }catch (Exception e ){}
    }
    private static boolean registerPermission(String name, String options, String defaultValue, boolean defaultReturn)
    {
        try (Connection connection = Emulator.getDatabase().getDataSource().getConnection())
        {
            try (PreparedStatement statement = connection.prepareStatement("ALTER TABLE  `permissions` ADD  `" + name +"` ENUM(  " + options + " ) NOT NULL DEFAULT  '" + defaultValue + "'"))
            {
                statement.execute();
                return true;
            }
        }
        catch (SQLException e)
        {}

        return defaultReturn;
    }

}
