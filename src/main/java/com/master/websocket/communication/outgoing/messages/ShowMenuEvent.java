package com.master.websocket.communication.outgoing.messages;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.master.LevelSystem.LevelSystemManager;
import com.master.LevelSystem.Player.LevelPlayer;
import com.master.LevelSystem.Tasks.Task;
import com.master.websocket.communication.outgoing.OutgoingMessage;

import java.sql.SQLException;

public class ShowMenuEvent extends OutgoingMessage {
    public ShowMenuEvent(String header, LevelPlayer player) throws  SQLException {
        super("ShowMenuEvent");

        if(player != null) {
            JsonArray Tarray = new JsonArray();
            JsonObject taskObj;
            for(Task task : player.getTasks().values()) {
                if (task.getType() == 'e') {
                    taskObj = new JsonObject();
                    taskObj.addProperty("id", task.getId());
                    taskObj.addProperty("name", task.getName());
                    taskObj.addProperty("description", task.getDescription());
                    taskObj.addProperty("image", task.getImage());
                    taskObj.addProperty("type", task.getType());
                    taskObj.addProperty("room_id", task.getRoomId());
                    taskObj.addProperty("XP", task.getXP());
                    Tarray.add(taskObj);
                }
            }
            this.data.addProperty("username", player.getPlayerInfo().getHabbo().getHabboInfo().getUsername());
            this.data.addProperty("look", player.getPlayerInfo().getHabbo().getHabboInfo().getLook());
            this.data.addProperty("level", player.getPlayerInfo().getLevel());
            this.data.addProperty("XP", player.getPlayerInfo().getXP());
            this.data.addProperty("max_XP", String.valueOf(LevelSystemManager.getLevelManager().Levels().get(player.getPlayerInfo().getLevel()).getXP()));
            this.data.add("tasks", Tarray);
            this.dataArray.add(this.data);
            this.outgoing.addProperty("header", header);
            this.outgoing.add("data", this.dataArray);

        }
    }

    @Override
    public String execute() {
        return this.outgoing.toString();
    }
}
