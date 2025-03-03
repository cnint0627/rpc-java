package org.example.common.util;

import cn.hutool.setting.dialect.Props;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;

@Slf4j
public class RpcConfigUtil {
    public static <T> T loadConfig(Class<T> targetClass, String prefix) {
        return loadConfig(targetClass, prefix, "");
    }
    public static <T> T loadConfig(Class<T> targetClass, String prefix, String env) {
        StringBuilder configFileNameBuilder = new StringBuilder("application");
        if (Strings.isNotBlank(env)) {
            configFileNameBuilder.append("-").append(env);
        }
        configFileNameBuilder.append(".properties");
        String configFileName = configFileNameBuilder.toString();
        Props properties = new Props(configFileName);
        if (properties.isEmpty()) {
            log.warn("配置文件 {} 不存在", configFileName);
            throw new RuntimeException();
        } else {
            log.info("加载配置文件: {}", configFileName);
        }
        try {
            return properties.toBean(targetClass, prefix);
        } catch (Exception e) {
            log.warn("配置文件 {} 加载失败", configFileName);
            throw new RuntimeException();
        }
    }
}
