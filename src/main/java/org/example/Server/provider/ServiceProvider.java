package org.example.Server.provider;

import java.util.HashMap;
import java.util.Map;

public class ServiceProvider {
    private Map<String, Object> interfaceProvider;
    public ServiceProvider() {
        this.interfaceProvider = new HashMap<>();
    }
    public void provideServiceInterface(Object service) {
        Class<?>[] interfaceName = service.getClass().getInterfaces();
        for (Class<?> clazz: interfaceName) {
            interfaceProvider.put(clazz.getName(), service);
        }
    }
    public Object getServiceInterface(String interfaceName) {
        return interfaceProvider.get(interfaceName);
    }
}
