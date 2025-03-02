package org.example.Server.provider;

import org.example.Server.rateLimit.RateLimit;
import org.example.Server.rateLimit.RateLimitProvider;
import org.example.Server.rateLimit.impl.TokenBucketRateLimit;
import org.example.Server.serviceRegister.ServiceRegister;
import org.example.Server.serviceRegister.impl.ZKServiceRegister;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class ServiceProvider {
    private String host;
    private int port;
    private Map<String, Object> interfaceProvider;
    private RateLimitProvider rateLimitProvider;
    private ServiceRegister serviceRegister;
    public ServiceProvider(String host, int port) {
        this.interfaceProvider = new HashMap<>();
        this.rateLimitProvider = new RateLimitProvider();
        this.serviceRegister = new ZKServiceRegister();
        this.host = host;
        this.port = port;
    }
    public void provideServiceInterface(Object service) {
        Class<?>[] interfaceName = service.getClass().getInterfaces();
        for (Class<?> clazz: interfaceName) {
            interfaceProvider.put(clazz.getName(), service);
            serviceRegister.register(clazz, new InetSocketAddress(host, port));
        }
    }
    public Object getServiceInterface(String interfaceName) {
        return interfaceProvider.get(interfaceName);
    }
    public boolean getServiceToken(String serviceName) {
        return rateLimitProvider.getRateLimit(serviceName).getToken();
    }
}
