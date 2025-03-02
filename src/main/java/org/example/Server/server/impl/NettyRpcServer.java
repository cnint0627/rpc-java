package org.example.Server.server.impl;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.example.Server.provider.ServiceProvider;
import org.example.Server.server.RpcServer;
import org.example.Server.netty.initializer.NettyServerInitializer;

@Slf4j
public class NettyRpcServer implements RpcServer {
    private ServiceProvider serviceProvider;
    private final NioEventLoopGroup bossGroup;
    private final NioEventLoopGroup workGroup;
    public NettyRpcServer(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
        this.bossGroup = new NioEventLoopGroup();
        this.workGroup = new NioEventLoopGroup();
    }
    @Override
    public void start(int port) {
        log.info("服务端已启动，端口 {}", port);
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new NettyServerInitializer(serviceProvider));
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("服务端启动失败: {}", e.getMessage());
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
    @Override
    public void stop() {
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
        log.info("服务端已关闭");
    }
}
