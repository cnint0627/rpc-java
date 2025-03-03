package org.example.server.server.impl;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.example.server.provider.ServiceProvider;
import org.example.server.server.RpcServer;
import org.example.server.netty.initializer.NettyServerInitializer;

@Slf4j
public class NettyRpcServer implements RpcServer {
    private ServiceProvider serviceProvider;
    private ChannelFuture channelFuture;
    public NettyRpcServer(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }
    @Override
    public void start(int port) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        log.info("Netty服务端已启动");
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new NettyServerInitializer(serviceProvider));
            channelFuture = serverBootstrap.bind(port).sync();
            log.info("Netty服务端已绑定端口 {}", port);
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.info("Netty服务端中断: {}", e.getMessage());
        } finally {
            shutDown(bossGroup, workGroup);
            log.info("Netty服务端已关闭");
        }
    }
    @Override
    public void stop() {
        if (channelFuture != null) {
            try {
                channelFuture.channel().close().sync();
                log.info("Netty服务端主通道已关闭");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.info("Netty服务端关闭中断: {}", e.getMessage());
            }
        } else {
            log.warn("Netty服务端尚未启动，无法关闭");
        }
    }
    private void shutDown(NioEventLoopGroup bossGroup, NioEventLoopGroup workGroup) {
        if (bossGroup != null) {
            bossGroup.shutdownGracefully().syncUninterruptibly();
        }
        if (workGroup != null) {
            workGroup.shutdownGracefully().syncUninterruptibly();
        }
    }
}
