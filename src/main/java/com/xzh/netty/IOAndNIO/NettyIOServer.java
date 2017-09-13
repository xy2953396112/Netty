package com.xzh.netty.IOAndNIO;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.oio.OioServerSocketChannelConfig;
import io.netty.util.CharsetUtil;

public class NettyIOServer {
   
	public void server(int port){
		
		final ByteBuf buf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Hi!\r\n",CharsetUtil.UTF_8));
		//时间循环组
		EventLoopGroup group = new NioEventLoopGroup();
		//引导服务器配置
		 ServerBootstrap  b = new ServerBootstrap();
		//使用IO阻塞模式
		b.group(group).channel( OioServerSocketChannelConfig.class).localAddress(new InetSocketAddress(port))
		//指定ChannelInitializer初始化handlers
		.childHandler(new ChannelInitializer<Channel>(){

			@Override
			protected void initChannel(Channel ch) throws Exception {
				// TODO Auto-generated method stub
				//添加一个"入站"handler到ChannelPipeline
				ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
					
					public void channelActive(ChannelHandlerContext ctx){
						//连接后,写消息到客户端，写完后关闭连接
						ctx.writeAndFlush(buf.duplicate()).addListener(ChannelFutureListener.CLOSE);
						
						
					}
				});
				
				
			
		}});
		
		//绑定服务器接受连接
		ChannelFuture f = b.bind().sync();
		f.channel().closeFuture().sync();
		//释放所有资源
		group.shutdownGracefully();
		
	}
}
