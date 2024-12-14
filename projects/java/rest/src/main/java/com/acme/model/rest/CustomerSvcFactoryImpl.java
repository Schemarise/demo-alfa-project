package com.acme.model.rest;

import demo.model.*;
import com.acme.model.*;

// The factory to create a CustomerSvc implementation. We reuse the one from the Basic project.
public class CustomerSvcFactoryImpl implements CustomerSvc.Factory {
    public CustomerSvcFactoryImpl() {
    }

    @Override
    public CustomerSvc create() {
        return new CustomerSvcImpl();
    }

}