package org.example.config;

import lombok.*;
import org.example.client.servicecenter.balance.impl.ConsistencyHashLoadBalance;
import org.example.common.serializer.serializer.Serializer;
import org.example.common.serializer.serializer.SerializerType;
import org.example.server.serviceregister.impl.ZKServiceRegister;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class RpcConfig {
    private String name = "rpc";
    private String host = "localhost";
    private Integer port = 1234;
    private String version = "1.0.0";
    private String register = new ZKServiceRegister().toString();
    private String serializer = Serializer.getSerializer(SerializerType.JSON.getCode()).toString();
    private String loadBalance = new ConsistencyHashLoadBalance().toString();
}
