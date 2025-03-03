package org.example.client.netty.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import org.example.client.netty.handler.NettyClientHandler;
import org.example.common.serializer.coder.Decoder;
import org.example.common.serializer.coder.Encoder;
import org.example.common.serializer.serializer.impl.JsonSerializer;

public class NettyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new Encoder(new JsonSerializer()));
        pipeline.addLast(new Decoder());
        pipeline.addLast(new NettyClientHandler());
    }
}
