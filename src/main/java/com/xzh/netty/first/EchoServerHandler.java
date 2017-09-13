package com.xzh.netty.first;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {
   
	public void channelRead(ChannelHandlerContext ctx,Object obj){
		System.out.println("Server received:"+obj);
		ctx.write(obj);	
	}
	
	public void channelReadComplete(ChannelHandlerContext ctx){
		
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
		
	}
	//捕获服务器异常
	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
		cause.printStackTrace();
		ctx.close();
	}
}
