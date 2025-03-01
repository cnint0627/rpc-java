package org.example.Client.client.impl;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import org.example.Client.client.RpcClient;
import org.example.Client.netty.initializer.NettyClientInitializer;
import org.example.Client.serviceCenter.ServiceCenter;
import org.example.Client.serviceCenter.impl.ZKServiceCenter;
import org.example.common.message.RpcRequest;
import org.example.common.message.RpcResponse;

import java.net.InetSocketAddress;

public class NettyRpcClient implements RpcClient {
    private static final Bootstrap bootstrap;
    private static final EventLoopGroup eventLoopGroup;
    private ServiceCenter serviceCenter;
    public NettyRpcClient() throws InterruptedException {
        serviceCenter = new ZKServiceCenter();
    }
    static {
        eventLoopGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new NettyClientInitializer());
    }
    @Override
    public RpcResponse sendRequest(RpcRequest request) {
        InetSocketAddress address = serviceCenter.serviceDiscovery(request.getInterfaceName());
        if (address == null) {
            return null;
        }
        String host = address.getHostName();
        int port = address.getPort();
        try {
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            Channel channel = channelFuture.channel();
            channel.writeAndFlush(request);
            channel.closeFuture().sync();
            AttributeKey<RpcResponse> key = AttributeKey.valueOf("RpcResponse");
            RpcResponse response = channel.attr(key).get();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }
}
