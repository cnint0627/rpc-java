package org.example.client.servicecenter.balance.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.client.servicecenter.balance.LoadBalance;

import java.util.*;

@Slf4j
public class ConsistencyHashLoadBalance implements LoadBalance {
    private static final int VIRTUAL_NUM = 5;
    private List<String> realNodes = new ArrayList<>();
    private SortedMap<Integer, String> shards = new TreeMap<>();
    @Override
    public String balance(List<String> addressList) {
        // 先更新节点环
        for (String node: realNodes) {
            if (!addressList.contains(node)) {
                delNode(node);
            }
        }
        for (String address: addressList) {
             if (!realNodes.contains(address)) {
                 addNode(address);
             }
        }
        int key = getHash(UUID.randomUUID().toString());
        SortedMap<Integer, String> subMap = shards.tailMap(key);
        if (subMap.isEmpty()) {
            key = shards.lastKey();
        } else {
            key = subMap.firstKey();
        }
        String node = shards.get(key);
        return node;
    }
    /**
     * FNV1_32_HASH算法
     */
    private static int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++)
            hash = (hash ^ str.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        // 如果算出来的值为负数则取其绝对值
        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }
    private void addNode(String node) {
        realNodes.add(node);
        log.info("真实节点 {} 上线添加", node);
        for (int i = 0; i < VIRTUAL_NUM; i++) {
            String virtualNode = node + "&&VN" + i;
            shards.put(getHash(virtualNode), node);
        }
    }
    private void delNode(String node) {
        realNodes.remove(node);
        log.info("真实节点 {} 下线移除", node);
        for (int i = 0; i < VIRTUAL_NUM; i++) {
            String virtualNode = node + "&&VN" + i;
            shards.remove(getHash(virtualNode));
        }
    }
}
