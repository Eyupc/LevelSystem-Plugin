package com.master.websocket.communication.outgoing.messages;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.master.LevelSystem.LevelSystemManager;
import com.master.LevelSystem.Player.LevelPlayer;
import com.master.websocket.communication.outgoing.OutgoingMessage;

import java.sql.SQLException;

public class ShowActionEvent extends OutgoingMessage {
    public ShowActionEvent(LevelPlayer player) throws  SQLException {
        super("ShowActionEvent");

        JsonArray Tarray = new JsonArray();
        JsonObject jsonObject;

           jsonObject = new JsonObject();
           jsonObject.addProperty("title","Oyunda Çevrimiçi Kal");
           jsonObject.addProperty("description","Oyunda çevrimiçi kaldığınız her 60 dakikada bir otomatik olarak 25 XP kazanırsınız.");
           jsonObject.addProperty("image","");
           jsonObject.addProperty("count",String.valueOf(player.getPlayerStats().getOnlineTime()));
           jsonObject.addProperty("XP","25");
           Tarray.add(jsonObject);

           jsonObject = new JsonObject();
           jsonObject.addProperty("title","Sohbet Et");
           jsonObject.addProperty("description","Karşılama odası içerisinde yazacağınız her 1000 mesajda bir 100 XP kazanırsınız.");
           jsonObject.addProperty("image","");
           jsonObject.addProperty("count",String.valueOf(player.getPlayerStats().getMessagesAmount()));
           jsonObject.addProperty("XP","100");
           Tarray.add(jsonObject);


           jsonObject = new JsonObject();
           jsonObject.addProperty("title","Saygı Duy");
           jsonObject.addProperty("description","Habron'da başka kullanıcılara duyduğunuz her 50 saygıda bir 125 XP kazanırsınız.");
           jsonObject.addProperty("image","");
           jsonObject.addProperty("count",String.valueOf(player.getPlayerStats().getRespectsGiven()));
           jsonObject.addProperty("XP","125");
           Tarray.add(jsonObject);

           jsonObject = new JsonObject();
           jsonObject.addProperty("title","Saygı Kazan");
           jsonObject.addProperty("description","Habron'da başka kullanıcılara duyduğunuz her 50 saygıda bir 125 XP kazanırsınız.");
           jsonObject.addProperty("image","");
           jsonObject.addProperty("count",String.valueOf(player.getPlayerStats().getRespects()));
           jsonObject.addProperty("XP","125");
           Tarray.add(jsonObject);


        this.data.addProperty("username", player.getPlayerInfo().getHabbo().getHabboInfo().getUsername());
        this.data.addProperty("look", player.getPlayerInfo().getHabbo().getHabboInfo().getLook());
        this.data.addProperty("level", player.getPlayerInfo().getLevel());
        this.data.addProperty("XP", player.getPlayerInfo().getXP());
        this.data.addProperty("max_XP", String.valueOf(LevelSystemManager.getLevelManager().Levels().get(player.getPlayerInfo().getLevel()).getXP()));

        this.data.add("tasks",Tarray);
        this.dataArray.add(this.data);
        this.outgoing.addProperty("header", header);
        this.outgoing.add("data", this.dataArray);
    }

    @Override
    public String execute() {
        return this.outgoing.toString();
    }
}
