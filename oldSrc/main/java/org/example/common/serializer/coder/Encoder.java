package org.example.common.serializer.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.AllArgsConstructor;
import org.example.common.message.MessageType;
import org.example.common.message.RpcRequest;
import org.example.common.message.RpcResponse;
import org.example.common.serializer.serializer.Serializer;

@AllArgsConstructor
public class Encoder extends MessageToByteEncoder {
    private Serializer serializer;
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        if (msg instanceof RpcRequest) {
            out.writeShort(MessageType.REQUEST.getCode());
        } else if (msg instanceof RpcResponse) {
            out.writeShort(MessageType.RESPONSE.getCode());
        }
        out.writeShort(serializer.getType());
        byte[] bytes = serializer.serialize(msg);
        out.writeShort(bytes.length);
        out.writeBytes(bytes);
    }
}
