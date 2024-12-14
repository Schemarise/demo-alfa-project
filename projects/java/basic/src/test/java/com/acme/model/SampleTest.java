package com.acme.model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.UUID;

import com.schemarise.alfa.runtime.Alfa;
import com.schemarise.alfa.runtime.BuilderConfig;
import com.schemarise.alfa.runtime.IBuilderConfig;
import org.junit.*;

import demo.model.*;
import com.schemarise.alfa.runtime.JsonCodec;
import com.schemarise.alfa.runtime.utils.AlfaRandomizer;

public class SampleTest {
    /**
     * We will create an Example Employee and print its JSON definition
     */
    @Test
    public void createEmployeeAndPrint() throws Exception {
        EmployeeKey k = EmployeeKey.builder().setId(UUID.randomUUID()).build();
        Employee bob = Employee.builder().
                            set$key(k).
                            setFirstName("Bob").
                            setLastName("Smith").
                            setJobTitle("Manager").
                            setDateOfBirth(LocalDate.of(2000, 10, 9)).
                            setSalary(100000.00).
                            putSkillsetLevels("ALFA", 8).
                            putSkillsetLevels("AWS", 6).build();

        String bobJson = Alfa.jsonCodec().toFormattedJson(bob);
        System.out.println(bobJson);
    }

    /**
     * We will create an Employee using the ALFA randomiser.
     */
    @Test
    public void createRandomEmployee() throws Exception {
        IBuilderConfig bc = BuilderConfig.newBuilder().setShouldValidateOnBuild(false).build();
        AlfaRandomizer ar = new AlfaRandomizer(bc, Collections.emptyList());
        Employee randObj = ar.random(Employee.EmployeeDescriptor.TYPE_NAME);
        String json = Alfa.jsonCodec().toFormattedJson(randObj);
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