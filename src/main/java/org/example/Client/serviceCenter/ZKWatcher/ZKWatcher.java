package org.example.Client.serviceCenter.ZKWatcher;

import lombok.AllArgsConstructor;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.example.Client.cache.ServiceCache;


@AllArgsConstructor
public class ZKWatcher {
    private CuratorFramework client;
    private ServiceCache cache;
    public void watchToUpdate(String path) throws InterruptedException {
        CuratorCache curatorCache = CuratorCache.build(client, "/");
        curatorCache.listenable().addListener(new CuratorCacheListener() {
            @Override
            public void event(Type type, ChildData oldData, ChildData data) {
                // 回调函数里面报错不会有输出
                String[] oldPathList = parseData(oldData);
                String[] pathList = parseData(data);
//                System.out.println("监听到注册中心变化: " + type.name());
//                System.out.println(oldPathList + " " + pathList);
                switch (type.name()) {
                    case "NODE_CREATED":
                        if (pathList.length <= 2) {
                            break;
                        }
                        cache.addServiceToCache(pathList[1], pathList[2]);
                        break;
                    case "NODE_CHANGED":
                        if (oldPathList.length <= 2 || pathList.length <= 2) {
                            break;
                        }
                        cache.replaceServiceAddress(oldPathList[1], oldPathList[2], pathList[2]);
                        break;
                    case "NODE_DELETED":
                        if (oldPathList.length <= 2) {
                            break;
                        }
                        cache.removeServiceAddress(oldPathList[1], oldPathList[2]);
                        break;
                    default:
                        break;
                }
            }
        });
        curatorCache.start();
    }
    private String[] parseData(ChildData data) {
        // ROOT_RPC/Service/127.0.0.1:1234
        if (data == null) {
            return null;
        }
        return data.getPath().split("/");
    }
}
