package com.master.websocket.communication.outgoing.messages;

import com.master.websocket.communication.outgoing.OutgoingMessage;

import java.sql.SQLException;

public class ShowMenuIconEvent extends OutgoingMessage {
    public ShowMenuIconEvent(String header, boolean show) throws  SQLException {
        super("ShowMenuIconEvent");
            this.data.addProperty("show_icon", show);
            // this.data.add("tasks", tasksobj);
            this.dataArray.add(this.data);
            this.outgoing.addProperty("header", header);
            this.outgoing.add("data", this.dataArray);
    }

    @Override
    public String execute() {
        return this.outgoing.toString();
    }
}
