package com.acme.model;

import java.util.stream.Collectors;

import acme.model.*;
import alfa.rt.utils.AlfaRandomizer;
import java.util.stream.*;
import java.util.*;

/**
 * CustomerSvc is defined as a ALFA service and is generated as a Java interface.
 * This Mock implementation is used in tests and the REST endpoint project.
 */
public class CustomerSvcImpl implements CustomerSvc {
    private AlfaRandomizer ar = new AlfaRandomizer();

    @Override
    public void create(Customer _c, boolean _nameCheck) {
    }

    @Override
    public Optional<Customer> get(CustomerKey _k) {
        return Optional.of(ar.random(Customer.TYPE_NAME));
    }

    @Override
    public List<Customer> getByType(CustomerType _t) {
        return getAllCustomers().stream().filter( e -> e.getCustType() == _t ).collect( Collectors.toList());
    }

    @Override
    public List<Customer> getAllCustomers() {
        // We simply create 20 random Customer objects using ALFA's randomiser for this example ...
        return IntStream.range(0, 20).boxed().map( i -> (Customer) ar.random(Customer.TYPE_NAME) ).collect( Collectors.toList());
    }
}
