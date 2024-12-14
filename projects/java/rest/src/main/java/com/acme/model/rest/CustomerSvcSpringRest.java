package com.demo.model.rest;

import com.acme.model.rest.CustomerSvcFactoryImpl;

/**
 * This file is generated as part of the ALFA Java code generation.
 * Note it implements the ALFA CustomerSvc service and delegates all calls to the single
 * implementation in basic/src/main/java/com/acme/model/CustomerSvcImpl.java.
 */
@org.springframework.web.bind.annotation.RestController(value = "CustomerSvc")
/** Customer Service API for CRUD and other operations */
public class CustomerSvcSpringRest implements demo.model.CustomerSvc {
    private final demo.model.CustomerSvc delegate;

    public CustomerSvcSpringRest() {
        this( new CustomerSvcFactoryImpl().create() );
    }

    public CustomerSvcSpringRest(demo.model.CustomerSvc delegate) {
        this.delegate = delegate;
    }

    @org.springframework.web.bind.annotation.RequestMapping(
            value = "/api/demo.model/create",
            method = org.springframework.web.bind.annotation.RequestMethod.POST)
    /** Create the give customer */
    public void create(
            @org.springframework.web.bind.annotation.RequestBody
                    /** The new customer */
                    demo.model.Customer _c,
            @org.springframework.web.bind.annotation.RequestBody
                    /** Ensure not already a customer */
                    boolean _nameCheck) {
        this.delegate.create(_c, _nameCheck);
    }

    @org.springframework.web.bind.annotation.RequestMapping(
            value = "/api/demo.model/get",
            method = org.springframework.web.bind.annotation.RequestMethod.POST)
    /** Fetch customer by key, if not exists, None returned */
    public java.util.Optional<demo.model.Customer> fetch(
            @org.springframework.web.bind.annotation.RequestBody
                    /** Key used for search */
                    demo.model.CustomerKey _k) {
        return this.delegate.fetch(_k);
    }

    @org.springframework.web.bind.annotation.RequestMapping(
            value = "/api/demo.model/getAllCustomers",
            method = org.springframework.web.bind.annotation.RequestMethod.POST)
    /** Get all customers */
    public java.util.List<demo.model.Customer> getAllCustomers() {
        return this.delegate.getAllCustomers();
    }

    @org.springframework.web.bind.annotation.RequestMapping(
            value = "/api/demo.model/getByType",
            method = org.springframework.web.bind.annotation.RequestMethod.POST)
    /** Get all customers for given type */
    public java.util.List<demo.model.Customer> getByType(
            @org.springframework.web.bind.annotation.RequestBody
                    /** Type to search for */
                    demo.model.CustomerType _t) {
        return this.delegate.getByType(_t);
    }
}
