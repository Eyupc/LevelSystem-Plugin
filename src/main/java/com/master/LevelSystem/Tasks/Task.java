package com.master.LevelSystem.Tasks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class Task {
    private int id;
    private String name;
    private String description;
    private String image;
    private char type;
    private int roomId = 0;
    private int XP;
    private int item_id;

    public Task(ResultSet set) throws SQLException {
        this.id = set.getInt("id");
        this.name = set.getString("name");
        this.description = set.getString("description");
        this.image = set.getString("image");
        this.type =  set.getString("type").charAt(0);
        this.roomId = set.getInt("room_id");
        this.item_id = set.getInt("item_id");
        this.XP = set.getInt("XP");
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public char getType() {
        return type;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getXP() {
        return XP;
    }
    public int getItemId(){
        return this.item_id;
    }
}
