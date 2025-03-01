package org.example.Client.serviceCenter.balance.impl;

import org.example.Client.serviceCenter.balance.LoadBalance;

import java.util.List;

public class RoundRobinLoadBalance implements LoadBalance {
    private int choice = -1;
    @Override
    public String balance(List<String> addressList) {
        choice = (choice + 1) % addressList.size();
        String address = addressList.get(choice);
        System.out.println("RoundRobin负载均衡选择了 " + address + " 服务器");
        return address;
    }
}
