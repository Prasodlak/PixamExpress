package com.greenfox.pixamexpress.services;

import com.greenfox.pixamexpress.models.Customer;

public interface CustomerService {

    Customer getCustomer(long id, String name, String address, String pizza);
}
