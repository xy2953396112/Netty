package com.xzh.netty.first;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
/**
 * 
 * @author lenovo
 * ServerBootstrap实例引导绑定和启动服务器
 * NioEventLoopGroup对象处理事件，如接受新连接、接受数据、写数据等
 * InetSocketAddress,服务器监听此端口
 * childHandler执行所有的连接请求
 * ServerBootstrap.bind()绑定服务器
 */
public class EchoServer {
      
	private final int port;
	
	public EchoServer(int port){
		this.port = port;
	}
	
	public void start() throws Exception{
		//NioEventLoopGroup接收和处理新连接
		EventLoopGroup group = new NioEventLoopGroup();
		//创建Bootstrap对象启动服务器	
	    ServerBootstrap b = new ServerBootstrap();
		//指定通道类型为NioServerSocketChannel   //让服务器监听某个端口
	    b.group(group).channel(NioServerSocketChannel.class).localAddress(port)
		  //childHandler指定连接后调用的ChannelHandler,ChannelInitializer是抽象类，需要实现抽象方法,用来设置ChannelHandler 
	      .childHandler(new ChannelInitializer<Channel>(){
			   
			@Override
			protected void initChannel(Channel ch) throws Exception {
				// TODO Auto-generated method stub
				ch.pipeline().addLast(new EchoServerHandler());
			}});
		//绑带服务器等待直到绑定完成，sync（）阻塞直到服务器完成绑定，
	    ChannelFuture f = b.bind().sync();
	    System.out.println(EchoServer.class.getName()+"started and listen on " +f.channel().localAddress());
	    f.channel().closeFuture().sync();
	    group.shutdownGracefully().sync();
		}
	
	public static void main(String[] args) throws Exception {
		new EchoServer(65535).start();
	}
	
}
   

