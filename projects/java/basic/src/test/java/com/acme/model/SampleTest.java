package com.acme.model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.UUID;

import alfa.rt.BuilderConfig;
import alfa.rt.IBuilderConfig;
import org.junit.*;

import acme.model.*;
import alfa.rt.JsonCodec;
import alfa.rt.utils.AlfaRandomizer;

public class SampleTest {
    /**
     * We will create an Example Employee and print its JSON definition
     */
    @Test
    public void createEmployeeAndPrint() throws Exception {
        EmployeeKey k = EmployeeKey.newBuilder().setId(UUID.randomUUID()).build();
        Employee bob = Employee.newBuilder().
                            set$key(k).
                            setFirstName("Bob").
                            setLastName("Smith").
                            setJobTitle("Manager").
                            setDateOfBirth(LocalDate.of(2000, 10, 9)).
                            setSalary(100000.00).
                            putSkillsetLevels("ALFA", 8).
                            putSkillsetLevels("AWS", 6).build();

        String bobJson = JsonCodec.toFormattedJson(bob);
        System.out.println(bobJson);
    }

    /**
     * We will create an Employee using the ALFA randomiser.
     */
    @Test
    public void createRandomEmployee() throws Exception {
        IBuilderConfig bc = BuilderConfig.newBuilder().setShouldValidateOnBuild(false).build();
        AlfaRandomizer ar = new AlfaRandomizer(bc, Collections.emptyList());
        Employee randObj = ar.random(Employee.TYPE_NAME);
        String json = JsonCodec.toFormattedJson(randObj);
        System.out.println(json);
    }

    /**
     * Test the service implementation
     */
    @Test
    public void testCustomerService() {
        CustomerSvc s = new CustomerSvcImpl();
        Assert.assertTrue( s.getAllCustomers().size() == 20 );
    }
}