package com.master.LevelSystem.Tasks;

import com.eu.habbo.Emulator;
import com.master.LevelSystem.Player.LevelPlayer;
import gnu.trove.map.hash.THashMap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskManager {

    private THashMap<Integer, Task> tasks = new THashMap<Integer,Task>();
    public TaskManager() {

    }
    public void updateTasks(){
        tasks.clear();
        try (Connection connection = Emulator.getDatabase().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM habron_levelsystem_tasks WHERE enabled = '1'"))
        {
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    tasks.put(resultSet.getInt("id"),new Task(resultSet));
                }
                resultSet.close();
                statement.close();
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public THashMap<Integer,Task> getTasksForPlayer(LevelPlayer player) throws SQLException {
        THashMap<Integer,Task> tasksToDo = new THashMap<Integer,Task>();
        for(int taskId : tasks.keySet()){
            if(!player.getPlayerInfo().getTasksDone().contains(taskId)){
             tasksToDo.put(taskId,tasks.get(taskId));
            }
        }
        return tasksToDo;

    }

    public THashMap<Integer, Task> getTasks(){
        return this.tasks;
    }

}
