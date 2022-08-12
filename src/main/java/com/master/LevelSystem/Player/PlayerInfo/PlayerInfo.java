package com.master.LevelSystem.Player.PlayerInfo;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.users.Habbo;
import com.master.LevelSystem.LevelSystemManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerInfo {

    private final int userid;

    private int level;
    private int XP;

    public PlayerInfo(ResultSet resultSet) throws SQLException {
        userid = resultSet.getInt("id"); //userid (habboId)
        level = resultSet.getInt("level");
        XP = resultSet.getInt("XP");

    }

    public Habbo getHabbo(){
        return Emulator.getGameEnvironment().getHabboManager().getHabbo(this.userid);
    }
    public int getLevel(){
        return this.level;
    }
    public void LevelUP(){
        this.level+=1;
    }

    public int getXP() {
        return XP;
    }

    public List<Integer> getTasksDone() throws SQLException {
        List<Integer> tasksIds = new ArrayList<>();
        try (Connection connection = Emulator.getDatabase().getDataSource().getConnection()) {
            try (PreparedStatement st = connection.prepareStatement("SELECT * FROM habron_levelsystem_users_data WHERE user_id = ?")) {
                st.setInt(1, this.userid);
                ResultSet resultSet = st.executeQuery();

                while (resultSet.next()) {
                    tasksIds.add(resultSet.getInt("level_id"));
                }
                st.close();
                resultSet.close();
            }
        }
        return tasksIds;
    }
    public void addTaskDone(int taskId){
        try(Connection conn = Emulator.getDatabase().getDataSource().getConnection()){
            try(PreparedStatement statement = conn.prepareStatement("INSERT INTO habron_levelsystem_users_data (user_id,level_id) VALUES (?,?);")){
                statement.setInt(1,this.userid);
                statement.setInt(2,taskId);
                statement.executeUpdate();
                statement.close();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    public void addXP(int XP){
        this.XP += XP;
        boolean val = true;
        while(val) {
            if (this.XP >= LevelSystemManager.getLevelManager().Levels().get(this.level).getXP()) {
                if (LevelSystemManager.getLevelManager().Levels().get(this.level + 1) != null) {
                    this.LevelUP();
                }else {
                    val = false;
                }
                this.update();
                //update also in db
            }else {
                val = false;
            }
        }
    }

    public void update(){
        try(Connection conn = Emulator.getDatabase().getDataSource().getConnection()){
            try(PreparedStatement statement = conn.prepareStatement("UPDATE habron_levelsystem_users SET level = ? , XP = ? WHERE id = ?")){
                statement.setInt(1,this.level);
                statement.setInt(2,this.XP);
                statement.setInt(3,this.getHabbo().getHabboInfo().getId());
                statement.executeUpdate();
                statement.close();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }



}
