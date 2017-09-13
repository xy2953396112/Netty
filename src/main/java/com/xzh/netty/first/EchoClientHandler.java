package com.xzh.netty.first;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
/**
 * 
 * @author lenovo
 * ChannelInboundHandlerAdapter处理完消息后需要负责释放资源,
 * SimpleChannelInboundHandler<ByteBuf> 完成channelRead0后释放消息，ByteBuf.release()来释放资源   
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    
	public void channelActive(ChannelHandlerContext ctx){
		ctx.write(Unpooled.copiedBuffer("Netty rocks!",CharsetUtil.UTF_8));
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Client received: "+ByteBufUtil.hexDump(msg.readBytes(msg.readableBytes())));
	}
    
	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
		
		cause.printStackTrace();
		ctx.close();
	}
}
