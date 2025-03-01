package org.example.common.serializer.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.example.common.message.MessageType;
import org.example.common.serializer.serializer.Serializer;

import java.util.List;

public class Decoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        short messageType = in.readShort();
        if (messageType != MessageType.REQUEST.getCode() && messageType != MessageType.RESPONSE.getCode()) {
            System.out.println("不支持该消息类型");
            return;
        }
        short serializeType = in.readShort();
        Serializer serializer = Serializer.getSerializer(serializeType);
        if (serializer == null) {
            System.out.println("不支持该序列化器");
            return;
        }
        int length = in.readShort();
        byte[] bytes = new byte[length];
        in.readBytes(bytes);
        out.add(serializer.deserialize(bytes, messageType));
    }
}
