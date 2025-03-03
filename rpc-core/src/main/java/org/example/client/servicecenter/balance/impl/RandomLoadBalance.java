package org.example.client.servicecenter.balance.impl;

import org.example.client.servicecenter.balance.LoadBalance;

import java.util.List;
import java.util.Random;

public class RandomLoadBalance implements LoadBalance {
    @Override
    public String balance(List<String> addressList) {
        Random random = new Random();
        int choice = random.nextInt(addressList.size());
        String address = addressList.get(choice);
        return address;
    }
}
