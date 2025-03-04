package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.common.serializer.serializer.Serializer;
import org.example.common.spi.SpiLoader;
import org.example.common.util.RpcConfigUtil;
import org.example.config.RpcConfig;
import org.example.config.RpcConstant;

@Slf4j
// 单例模式
public class RpcApplication {
    private static volatile RpcConfig rpcConfigInstance;
    public static void initialize(RpcConfig config) {
        // spi创建序列化器字符串到序列化器的映射
        SpiLoader.loadSpi(Serializer.class);
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
            synchronized (RpcConfig.class) {
                if (rpcConfigInstance == null) {
                    initialize();
                }
            }
        }
        return rpcConfigInstance;
    }
}
