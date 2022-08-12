package com.master.websocket.communication.incoming.messages;

import com.master.websocket.Habbo.SSOClientManager;
import com.master.websocket.communication.incoming.IncomingMessage;
import gnu.trove.map.hash.THashMap;
import io.netty.channel.Channel;

public class SSOTicketEvent extends IncomingMessage {

    public SSOTicketEvent(String header, THashMap<String,Object> data, Channel client) {
        super(header, data, client);
        this.execute();
    }


    @Override
    public void execute() {
        SSOClientManager.addClient((String) data.get("SSO"),this.client);

        //client.writeAndFlush(new BinaryWebSocketFrame(client.alloc().buffer().writeBytes(bytes)));
    }

}
