package org.example.Server.serviceRegister;

import java.net.InetSocketAddress;

public interface ServiceRegister {
    void register(Class<?> clazz, InetSocketAddress serviceAddress);
}
