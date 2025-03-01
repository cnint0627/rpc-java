package org.example.Client.serviceCenter.balance.impl;

import org.example.Client.serviceCenter.balance.LoadBalance;

import java.util.List;
import java.util.Random;

public class RandomLoadBalance implements LoadBalance {
    @Override
    public String balance(List<String> addressList) {
        Random random = new Random();
        int choice = random.nextInt(addressList.size());
        String address = addressList.get(choice);
        System.out.println("Random " + address + " 服务器");
        return address;
    }
}
