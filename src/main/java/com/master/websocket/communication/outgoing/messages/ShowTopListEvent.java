package com.master.websocket.communication.outgoing.messages;

import com.eu.habbo.habbohotel.users.HabboManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.master.LevelSystem.LevelSystemManager;
import com.master.LevelSystem.Player.LevelPlayer;
import com.master.websocket.communication.outgoing.OutgoingMessage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ShowTopListEvent extends OutgoingMessage {
    public ShowTopListEvent(LevelPlayer player) throws  SQLException {
        super("ShowTopListEvent");

        JsonArray Tarray = new JsonArray();
        JsonObject jsonObject;
        LinkedHashMap<Integer,Integer> toplist = LevelSystemManager.getTopList();
        ArrayList<Integer> ids = new ArrayList<Integer>(toplist.keySet());
        ArrayList<Integer> levels = new ArrayList<Integer>(toplist.values());

        for(int i = 0; i<=toplist.keySet().size()-1; i++){
            jsonObject = new JsonObject();
            jsonObject.addProperty("avatar", HabboManager.getOfflineHabboInfo(ids.get(i)).getLook());
            jsonObject.addProperty("username", HabboManager.getOfflineHabboInfo(ids.get(i)).getUsername());
            jsonObject.addProperty("level", levels.get(i));
            Tarray.add(jsonObject);
        }

        this.data.addProperty("username", player.getPlayerInfo().getHabbo().getHabboInfo().getUsername());
        this.data.addProperty("look", player.getPlayerInfo().getHabbo().getHabboInfo().getLook());
        this.data.addProperty("level", player.getPlayerInfo().getLevel());
        this.data.addProperty("XP", player.getPlayerInfo().getXP());
        this.data.addProperty("max_XP", String.valueOf(LevelSystemManager.getLevelManager().Levels().get(player.getPlayerInfo().getLevel()).getXP()));

        this.data.add("users",Tarray);
        this.dataArray.add(this.data);
        this.outgoing.addProperty("header", header);
        this.outgoing.add("data", this.dataArray);
    }

    @Override
    public String execute() {
        return this.outgoing.toString();
    }
}
