package org.example.Client.serviceCenter.balance;

import java.util.List;

public interface LoadBalance {
    String balance(List<String> addressList);
}
