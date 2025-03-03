package org.example.server.netty.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import lombok.AllArgsConstructor;

import org.example.server.provider.ServiceProvider;
import org.example.server.netty.handler.NettyServerHandler;
import org.example.common.serializer.coder.Decoder;
import org.example.common.serializer.coder.Encoder;
import org.example.common.serializer.serializer.impl.JsonSerializer;

@AllArgsConstructor
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    private ServiceProvider serviceProvider;
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new Encoder(new JsonSerializer()));
        pipeline.addLast(new Decoder());
        pipeline.addLast(new NettyServerHandler(serviceProvider));
    }
}
