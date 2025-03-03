package org.example.client.servicecenter.balance;

import java.util.List;

public interface LoadBalance {
    String balance(List<String> addressList);
}
