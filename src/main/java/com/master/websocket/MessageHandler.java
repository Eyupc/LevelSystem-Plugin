package com.master.websocket;

import com.master.websocket.Habbo.HabboClientManager;
import com.master.websocket.communication.incoming.Incoming;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;

import java.nio.charset.StandardCharsets;

public class MessageHandler extends SimpleChannelInboundHandler<BinaryWebSocketFrame> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //Nothing
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        HabboClientManager.killClient(ctx.channel().id());
        ctx.channel().close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    System.out.println(cause.toString());
        }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, BinaryWebSocketFrame textWebSocketFrame) throws Exception {
        new Incoming(textWebSocketFrame.content().toString(StandardCharsets.UTF_8),channelHandlerContext.channel());
    }




}
