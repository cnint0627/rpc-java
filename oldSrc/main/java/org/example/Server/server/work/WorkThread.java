package org.example.server.server.work;

import lombok.AllArgsConstructor;
import org.example.server.provider.ServiceProvider;
import org.example.common.message.RpcRequest;
import org.example.common.message.RpcResponse;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

@AllArgsConstructor
public class WorkThread implements Runnable {
    private Socket socket;
    private ServiceProvider serviceProvider;
    @Override
    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest) in.readObject();
            RpcResponse rpcResponse = getResponse(rpcRequest);
            out.writeObject(rpcResponse);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public RpcResponse getResponse(RpcRequest rpcRequest) {
        String interfaceName = rpcRequest.getInterfaceName();
        Object service = serviceProvider.getServiceInterface(interfaceName);
        try {
            Method method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamsType());
            Object invoke = method.invoke(service, rpcRequest.getParams());
            return RpcResponse.success(invoke);
        } catch (Exception e) {
            e.printStackTrace();
            return RpcResponse.fail();
        }
    }
}
