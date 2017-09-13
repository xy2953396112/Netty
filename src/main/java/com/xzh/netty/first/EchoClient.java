package com.xzh.netty.first;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class EchoClient {
  
	private final String host;
	private final int port;
	
	public EchoClient(String host,int port){
		this.host = host;
		this.port = port;
	}
	
	public void start() throws InterruptedException{
		//EventLoopGroup可以理解为一个线程池,用来处理连接，接受数据，发送数据
		EventLoopGroup group = new NioEventLoopGroup();
		
		Bootstrap b = new Bootstrap();
		
		b.group(group).channel(NioSocketChannel.class).remoteAddress(new InetSocketAddress(host,port))
		  .handler(new ChannelInitializer<SocketChannel>(){

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				// TODO Auto-generated method stub
				ch.pipeline().addLast(new EchoClientHandler());
			}
			  
		  });
		ChannelFuture f = b.connect().sync();
		f.channel().closeFuture().sync();
		group.shutdownGracefully().sync();
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		new EchoClient("localhost",65535).start();
		
	}
}
