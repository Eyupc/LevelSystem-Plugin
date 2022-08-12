package com.master.websocket.communication.incoming;

import com.master.utils.GSON;
import gnu.trove.map.hash.THashMap;
import io.netty.channel.Channel;

import java.lang.reflect.InvocationTargetException;

public class Incoming {


    public Incoming(String incoming, Channel client) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        IncomingData incomingData = GSON.getGson().fromJson(incoming,IncomingData.class);

         Class<?extends IncomingMessage> aClass= IncomingManager.getInstance().getMessages().get(incomingData.header);
         aClass.getDeclaredConstructor(new Class[]{String.class, THashMap.class, Channel.class}).newInstance(incomingData.header,incomingData.data[0],client);

    }


   private static class IncomingData {
        public  String header;

        public THashMap<String,Object>[] data;
    }

}
