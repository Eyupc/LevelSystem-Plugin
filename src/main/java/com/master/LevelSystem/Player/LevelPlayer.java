package com.master.LevelSystem.Player;

import com.eu.habbo.Emulator;
import com.master.LevelSystem.LevelSystemManager;
import com.master.LevelSystem.Player.PlayerInfo.PlayerInfo;
import com.master.LevelSystem.Player.PlayerInfo.PlayerStats;
import com.master.LevelSystem.Tasks.Task;
import gnu.trove.map.hash.THashMap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LevelPlayer {

    private final PlayerInfo playerInfo;
    private final PlayerStats playerStats;
    private THashMap<Integer, Task> tasks = new THashMap<>();
    public LevelPlayer(ResultSet set) throws SQLException {
        this.playerInfo = new PlayerInfo(set);
        this.tasks = LevelSystemManager.getTaskManager().getTasksForPlayer(this);
        this.playerStats = new PlayerStats(set.getInt("id"),this);
    }
    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    public PlayerStats getPlayerStats() {
        return playerStats;
    }

    public static LevelPlayer getLevelPlayer(Integer habboId) throws SQLException {
        LevelPlayer player = null;
        try(Connection connection = Emulator.getDatabase().getDataSource().getConnection()){
            try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM habron_levelsystem_users WHERE id = ? LIMIT 1")){
                preparedStatement.setInt(1,habboId);
                ResultSet set = preparedStatement.executeQuery();
                while(set.next()) {
                player =  new LevelPlayer(set);
                }
                preparedStatement.close();
                set.close();
                return player;


            }

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void createLevelPlayer(int habboId){
        try(Connection connection = Emulator.getDatabase().getDataSource().getConnection()){
            try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO habron_levelsystem_users(id,level,XP) VALUES (?,?,?);")) {
                preparedStatement.setInt(1, habboId);
                preparedStatement.setInt(2, 1);
                preparedStatement.setInt(3, 0);
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO habron_levelsystem_users_info(user_id,respects," +
                "respects_given,messages_amount,online_time) VALUES (?,?,?,?,?);")){
                preparedStatement.setInt(1,habboId);
                preparedStatement.setInt(2,0);
                preparedStatement.setInt(3,0);
                preparedStatement.setInt(4,0);
                preparedStatement.setInt(5,0);
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public THashMap<Integer,Task> getTasks(){
        return this.tasks;
    }
    public void updateTasks() throws SQLException {
        this.tasks = LevelSystemManager.getTaskManager().getTasksForPlayer(this);
    }

    public List<Integer> getTasksRoomIds(){
        List<Integer> list = new ArrayList<>();
        this.tasks.values().forEach((task)->list.add(task.getRoomId()));
        return list;
    }


}
