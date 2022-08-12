package com.master;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.plugin.EventHandler;
import com.eu.habbo.plugin.EventListener;
import com.eu.habbo.plugin.HabboPlugin;
import com.eu.habbo.plugin.events.emulator.EmulatorLoadedEvent;
import com.master.LevelSystem.Habbo.RegisterUtils;
import com.master.LevelSystem.Habbo.Timer.GameTimer;
import com.master.LevelSystem.LevelSystemManager;
import com.master.websocket.WebSocketServer;
import com.master.websocket.communication.incoming.IncomingManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;

public class Main extends HabboPlugin implements EventListener {
    private static final Logger LOGGER =  LoggerFactory.getLogger(Main.class);
    public static Main INSTANCE = null;

    @Override
    public void onEnable() throws Exception {
        INSTANCE = this;
        Emulator.getPluginManager().registerEvents(this, this);

    }

    @Override
    public void onDisable() throws Exception {

          }
    @EventHandler
    public void onEmu(EmulatorLoadedEvent e) throws Exception {

        LOGGER.info("[WS] WebSocket Plugin started - MasterEyup");
       RegisterUtils registerUtils = new RegisterUtils();//habbo
        registerUtils.Init(); //habbo

        Emulator.getConfig().register("habron.ws.host","0.0.0.0");
        Emulator.getConfig().register("habron.ws.port","2083");


        WebSocketServer webSocketServer = new WebSocketServer();
        webSocketServer.initializePipeline();
        webSocketServer.connect();

        IncomingManager.getInstance().registerMessages();

        LevelSystemManager mng = new LevelSystemManager(); //LevelSystem

        Timer timer = new Timer();
        TimerTask task = new GameTimer();
        timer.schedule(task,0,(1000*60));

    }

    @Override
    public boolean hasPermission(Habbo habbo, String s) {
        return false;
    }






    public static void main(String[] args) throws Exception {
    }
}
