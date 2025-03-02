package org.example.Server.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;
import org.example.Server.provider.ServiceProvider;
import org.example.common.message.RpcRequest;
import org.example.common.message.RpcResponse;

import java.lang.reflect.Method;

@AllArgsConstructor
public class NettyServerHandler extends SimpleChannelInboundHandler<RpcRequest> {
    private ServiceProvider serviceProvider;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequest request) throws Exception {
        RpcResponse response = getResponse(request);
        ctx.writeAndFlush(response);
        ctx.close();
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    public RpcResponse getResponse(RpcRequest rpcRequest) {
        String interfaceName = rpcRequest.getInterfaceName();
        if (!serviceProvider.getServiceToken(interfaceName)) {
            System.out.println("服务 " + interfaceName + " 限流");
            return RpcResponse.fail("服务 " + interfaceName + " 限流");
        }
        Object service = serviceProvider.getServiceInterface(interfaceName);
        try {
            Method method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamsType());
            Object result = method.invoke(service, rpcRequest.getParams());
            return RpcResponse.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            return RpcResponse.fail();
        }
    }
}
