package com.master.websocket.communication.incoming;

import com.master.websocket.communication.incoming.messages.*;
import gnu.trove.map.hash.THashMap;

public class IncomingManager {
    private static IncomingManager instance = null;
    private THashMap<String, Class<?extends IncomingMessage>> messages = new THashMap<>();

    public IncomingManager(){
        this.registerMessages();
    }

    public static IncomingManager getInstance(){
        if(instance == null)
            return instance = new IncomingManager();
        else
            return instance;
    }

    public void registerMessages(){
        this.messages.put("SSOTicketEvent", SSOTicketEvent.class);
        this.messages.put("PingEvent", PingEvent.class);
        this.messages.put("OpenMenuEvent", OpenMenuEvent.class);
        this.messages.put("GoToRoomEvent", GoToRoomEvent.class);
        this.messages.put("RequestTopListEvent", RequestTopListEvent.class);
        this.messages.put("RequestActionEvent",RequestActionEvent.class);
    }

    public THashMap<String,Class<?extends IncomingMessage>> getMessages(){
    return this.messages;
    }

}
