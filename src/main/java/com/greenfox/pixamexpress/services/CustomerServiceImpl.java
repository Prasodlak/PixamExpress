package com.greenfox.pixamexpress.services;

import com.greenfox.pixamexpress.models.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public Customer getCustomer(long id, String name, String address, String pizza) {
        return new Customer(id, name, address, pizza);
    }
}
