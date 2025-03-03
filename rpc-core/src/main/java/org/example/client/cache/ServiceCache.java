package org.example.client.cache;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ServiceCache {
    private static Map<String, List<String>> cache = new HashMap<>();
    public void addServiceToCache(String serviceName, String address) {
        if (cache.containsKey(serviceName)) {
            cache.get(serviceName).add(address);
        } else {
            List<String> addressList = new ArrayList<>();
            addressList.add(address);
            cache.put(serviceName, addressList);
        }
        log.info("新增 {} 的本地缓存: {}", serviceName, address);
    }
    public void removeServiceAddress(String serviceName, String address) {
        if (cache.containsKey(serviceName)) {
            cache.get(serviceName).remove(address);
            log.info("删除 {} 的本地缓存: {}", serviceName, address);
        } else {
            log.error("服务 {} 不存在，缓存删除失败", serviceName);
        }
    }
    public void replaceServiceAddress(String serviceName, String oldAddress, String newAddress) {
        if (cache.containsKey(serviceName)) {
            List<String> addressList = cache.get(serviceName);
            addressList.remove(oldAddress);
            addressList.add(newAddress);
            log.info("替换 {} 的本地缓存: {} -> {}", serviceName, oldAddress, newAddress);
        } else {
            log.error("服务 {} 不存在，缓存替换失败", serviceName);
        }
    }
    public List<String> getServiceFromCache(String serviceName) {
        List<String> addressList = cache.getOrDefault(serviceName, null);
        if (addressList != null && !addressList.isEmpty()) {
            log.info("从本地缓存获取服务 {}", serviceName);
            return addressList;
        } else {
            return null;
        }
    }
}
