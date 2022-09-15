package com.master.LevelSystem.Habbo.habboEvents;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.users.HabboItem;
import com.eu.habbo.plugin.EventHandler;
import com.eu.habbo.plugin.EventListener;
import com.eu.habbo.plugin.events.users.UserTakeStepEvent;
import com.master.LevelSystem.LevelSystemManager;
import com.master.LevelSystem.Player.LevelPlayer;
import com.master.LevelSystem.Tasks.Task;

import java.sql.SQLException;

public class StepOnTaskTileEvent implements EventListener {
    @EventHandler //When user walks on tile (chosen in db) the task is done. Receive XP
    public static void onWalk(UserTakeStepEvent ev) throws SQLException {
        if(ev.isCancelled())
            return;

        LevelPlayer player = LevelSystemManager.getOnlinePlayers().get(ev.habbo.getHabboInfo().getId());
        int roomId = ev.habbo.getHabboInfo().getCurrentRoom().getId();
        if(player == null)
            return;
        
        if(!player.getTasksRoomIds().contains(roomId))
            return;
        else {
            Task taskx = player.getTasks().values().stream().filter((task)->String.valueOf(task.getRoomId()).equals(String.valueOf(roomId))).findFirst().get();
            HabboItem item = ev.habbo.getHabboInfo().getCurrentRoom().getHabboItem(taskx.getItemId());
            if(item == null)
                return;

            if(item.getY() == ev.toLocation.y && item.getX() == ev.toLocation.x){
                player.getPlayerInfo().addTaskDone(taskx.getId());
                player.updateTasks();
                player.getPlayerInfo().addXP(taskx.getXP());
                ev.habbo.alert(Emulator.getTexts().getValue("levelsystem.task_done").replace(
                    "%XP%",String.valueOf(taskx.getXP())));
            }else {
                return;
            }

        }



    }
}
