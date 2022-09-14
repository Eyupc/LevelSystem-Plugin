package com.master.LevelSystem.Player.PlayerInfo;

import com.eu.habbo.Emulator;
import com.master.LevelSystem.Player.LevelPlayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerStats {
    private int user_id;
    private int respects;
    private int respects_given;
    private int messages_amount;
    private int online_time; //in minutes

    private final LevelPlayer player;



    public PlayerStats(int habboId,LevelPlayer player) throws SQLException {
    try(Connection conn = Emulator.getDatabase().getDataSource().getConnection()){
        try(PreparedStatement st = conn.prepareStatement("SELECT * FROM habron_levelsystem_users_info WHERE user_id = " + habboId + " LIMIT 1")) {
            ResultSet set = st.executeQuery();
            while (set.next()) {
                this.user_id = set.getInt("user_id");
                this.respects = set.getInt("respects");
                this.respects_given = set.getInt("respects_given");
                this.messages_amount = set.getInt("messages_amount");
                this.online_time = set.getInt("online_time");
            }
        }
    }
     this.player = player;

    }


    public void addRespect(int num){
        //this.respects += num;
        int mod = ((this.respects+ num) % 50);
        if((mod == 0) && this.player != null){
            this.respects+= num;
            this.player.getPlayerInfo().addXP(125);
            this.update();
            this.player.getPlayerInfo().update();
            return;
        }
        this.respects+= num;
    }

    public void addRespectsGiven(int num){
        int mod = ((this.respects_given+ num) % 50);
        if((mod == 0) && this.player != null){
            this.respects_given+= num;
            this.player.getPlayerInfo().addXP(125);
            this.update();
            this.player.getPlayerInfo().update();
            return;
        }
        this.respects_given+= num;
    }

    public void addMessagesAmount(int num){
        int mod = ((this.messages_amount+ num) % 1000);
        if((mod == 0 || mod == 1) && ((this.messages_amount % 1000) != 0) && this.player != null){
            this.messages_amount+=num;
            this.player.getPlayerInfo().addXP(100);
            this.update();
            this.player.getPlayerInfo().update();
            return;
        }
        this.messages_amount+=num;
    }
    public void addOnlineTime(int num){
        int mod = ((this.online_time+ num) % 60);
        if((mod == 0) && this.player != null){
            this.online_time +=num;
            this.player.getPlayerInfo().addXP(25);
            this.update();
            this.player.getPlayerInfo().update();
            return;
        }
        this.online_time +=num;
    }

    public int getUserId() {
        return user_id;
    }

    public int getRespects() {
        return respects;
    }

    public int getRespectsGiven() {
        return respects_given;
    }

    public int getMessagesAmount() {
        return messages_amount;
    }

    public int getOnlineTime() {
        return online_time;
    }

    public void update(){
        try(Connection conn = Emulator.getDatabase().getDataSource().getConnection()){
            try(PreparedStatement statement = conn.prepareStatement("UPDATE habron_levelsystem_users_info SET respects = ? , respects_given = ? , " +
                "messages_amount = ? , online_time = ?  WHERE user_id = ?")){
                statement.setInt(1,this.respects);
                statement.setInt(2,this.respects_given);
                statement.setInt(3,this.messages_amount);
                statement.setInt(4,this.online_time);
                statement.setInt(5,this.user_id);
                statement.executeUpdate();
                statement.close();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

}
