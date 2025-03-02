package org.example.Server.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Server.provider.ServiceProvider;
import org.example.common.message.RpcRequest;
import org.example.common.message.RpcResponse;

import java.lang.reflect.Method;

@AllArgsConstructor
@Slf4j
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
        String serviceName = rpcRequest.getInterfaceName();
        if (!serviceProvider.getServiceToken(serviceName)) {
            log.info("服务 {} 限流", serviceName);
            return RpcResponse.fail("服务限流");
        }
        Object service = serviceProvider.getServiceInterface(serviceName);
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
