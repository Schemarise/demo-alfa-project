package com.acme.model.avro;

import com.schemarise.alfa.runtime.codec.CodecConfig;
import com.schemarise.alfa.runtime.utils.AlfaRandomizer;
import com.schemarise.rt.lib.avro.AvroCodec;
import org.junit.*;
import org.apache.avro.generic.GenericRecord;

import com.schemarise.alfa.runtime.BuilderConfig;
import com.schemarise.alfa.runtime.IBuilderConfig;

import java.io.*;
import java.util.*;

import demo.model.*;

public class AvroConversionTest {
    private IBuilderConfig bc = BuilderConfig.newBuilder().setShouldValidateOnBuild(false).build();
    private CodecConfig ccb = new CodecConfig.Builder().setShouldValidateOnBuild(false).build();
    private AlfaRandomizer ar = new AlfaRandomizer(bc, Collections.emptyList());

    @Test
    @Ignore
    public void testAlfa2AvroRoundTrip() throws Exception {
        Employee obj = ar.random(Employee.EmployeeDescriptor.TYPE_NAME);

        OutputStream baos = new ByteArrayOutputStream();

        // Can write direct to a stream
        AvroCodec ac = new AvroCodec();
        ac.exportObj(obj, baos);
        ac.exportObj( obj, baos );

        // Or export as an Avro record
        GenericRecord gr = ac.exportToRecord(obj);
        
        System.out.println( gr );
        Assert.assertTrue( gr.get("LastName") != null );        
        Assert.assertTrue( gr.get("Salary") != null );        

        // For round tripping, read the Avro record to recreate Alfa object
        Employee decoded = ac.importObj(gr);

        // Assert what was round-tripped and original object matches
        Assert.assertEquals(obj, decoded);        
    }
}