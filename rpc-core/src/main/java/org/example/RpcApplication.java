package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.common.util.RpcConfigUtil;
import org.example.config.RpcConfig;
import org.example.config.RpcConstant;

@Slf4j
public class RpcApplication {
    private static volatile RpcConfig rpcConfigInstance;
    public static void initialize(RpcConfig config) {
        rpcConfigInstance = config;
        log.info("RPC框架初始化，配置: {}", config);
    }
    public static void initialize() {
        RpcConfig config;
        try {
            config = RpcConfigUtil.loadConfig(RpcConfig.class, RpcConstant.CONFIG_PREFIX);
            log.info("配置文件加载成功，配置文件: {}", RpcConstant.CONFIG_PREFIX);
        } catch (Exception e) {
            config = new RpcConfig();
            log.warn("配置文件加载失败，使用默认配置");
        }
        initialize(config);
    }
    public static RpcConfig getRpcConfig() {
        if (rpcConfigInstance == null) {
            synchronized (RpcApplication.class) {
                if (rpcConfigInstance == null) {
                    initialize();
                }
            }
        }
        return rpcConfigInstance;
    }
}
