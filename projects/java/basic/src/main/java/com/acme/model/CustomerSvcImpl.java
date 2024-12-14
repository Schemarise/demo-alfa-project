package com.acme.model;

import java.util.stream.Collectors;

import demo.model.*;
import com.schemarise.alfa.runtime.BuilderConfig;
import com.schemarise.alfa.runtime.IBuilderConfig;
import com.schemarise.alfa.runtime.utils.AlfaRandomizer;
import java.util.stream.*;
import java.util.*;

/**
 * CustomerSvc is defined as a ALFA service and is generated as a Java interface.
 * This Mock implementation is used in tests and the REST endpoint project.
 */
public class CustomerSvcImpl implements CustomerSvc {
    private IBuilderConfig bc = BuilderConfig.newBuilder().setShouldValidateOnBuild(false).build();
    private AlfaRandomizer ar = new AlfaRandomizer(bc, Collections.emptyList());

    @Override
    public void create(Customer _c, boolean _nameCheck) {
    }

    @Override
    public Optional<Customer> fetch(CustomerKey _k) {
        return Optional.of(ar.random(Customer.CustomerDescriptor.TYPE_NAME));
    }

    @Override
    public List<Customer> getByType(CustomerType _t) {
        return getAllCustomers().stream().filter( e -> e.getCustType() == _t ).collect( Collectors.toList());
    }

    @Override
    public List<Customer> getAllCustomers() {
        // We simply create 20 random Customer objects using ALFA's randomiser for this example ...
        return IntStream.range(0, 20).boxed().map( i -> (Customer) ar.random(Customer.CustomerDescriptor.TYPE_NAME) ).collect( Collectors.toList());
    }
}
