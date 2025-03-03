package org.example.server.serviceregister;

import java.net.InetSocketAddress;

public interface ServiceRegister {
    void register(Class<?> clazz, InetSocketAddress serviceAddress);
}
