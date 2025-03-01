package org.example.Client.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        System.out.println("新增 " + serviceName + " 的本地缓存: " + address);
    }
    public void removeServiceAddress(String serviceName, String address) {
        if (cache.containsKey(serviceName)) {
            cache.get(serviceName).remove(address);
            System.out.println("删除 " + serviceName + " 的本地缓存: " + address);
        } else {
            System.out.println("服务不存在");
        }
    }
    public void replaceServiceAddress(String serviceName, String oldAddress, String newAddress) {
        if (cache.containsKey(serviceName)) {
            List<String> addressList = cache.get(serviceName);
            addressList.remove(oldAddress);
            addressList.add(newAddress);
            System.out.println("替换 " + serviceName + " 的本地缓存: " + oldAddress + " --> " + newAddress);
        } else {
            System.out.println("服务不存在");
        }
    }
    public List<String> getServiceFromCache(String serviceName) {
        return cache.getOrDefault(serviceName, null);
    }
}
