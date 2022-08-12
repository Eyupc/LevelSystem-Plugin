package com.master.LevelSystem;

import com.eu.habbo.Emulator;
import com.master.LevelSystem.Levels.LevelManager;
import com.master.LevelSystem.Player.LevelPlayer;
import com.master.LevelSystem.Tasks.TaskManager;
import gnu.trove.map.hash.THashMap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class LevelSystemManager {
    private static THashMap<Integer,LevelPlayer> onlinePlayers = new THashMap<Integer,LevelPlayer>();
    private static TaskManager taskManager;
    private static LevelManager levelManager;
    public LevelSystemManager() throws SQLException {
        taskManager = new TaskManager();
        levelManager = new LevelManager();
        taskManager.updateTasks();
        levelManager.updateLevels();
    }

    public static void addOnlinePlayer(LevelPlayer player){
        onlinePlayers.put(player.getPlayerInfo().getHabbo().getHabboInfo().getId(),player);
    }
    public static THashMap<Integer,LevelPlayer> getOnlinePlayers(){
        return onlinePlayers;
    }

    public static void removeOnlinePlayer(int id){
        onlinePlayers.remove(id);
    }

    public static LinkedHashMap<Integer, Integer> getTopList() throws SQLException {
        LinkedHashMap<Integer,Integer> topList = new LinkedHashMap<>(); //habboId, Level
        try(Connection conn = Emulator.getDatabase().getDataSource().getConnection()){
            try(PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM habron_levelsystem_users ORDER BY XP DESC LIMIT 10;")){
                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    topList.put(resultSet.getInt("id"),resultSet.getInt("level"));
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return topList;
    }

    public static TaskManager getTaskManager(){
        return taskManager;
    }
    public static LevelManager getLevelManager(){
        return levelManager;
    }
}
