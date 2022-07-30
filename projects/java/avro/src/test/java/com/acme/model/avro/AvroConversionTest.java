package com.acme.model.avro;

import alfa.rt.codec.CodecConfig;
import org.junit.*;
import org.apache.avro.generic.GenericRecord;

import alfa.rt.BuilderConfig;
import alfa.rt.IBuilderConfig;

import alfa.rt.codec.avro.*;
import java.io.*;
import java.util.*;

import acme.model.*;
import alfa.rt.utils.AlfaRandomizer;

public class AvroConversionTest {
    private IBuilderConfig bc = BuilderConfig.newBuilder().setShouldValidateOnBuild(false).build();
    private CodecConfig ccb = new CodecConfig.Builder().setShouldValidateOnBuild(false).build();
    private AlfaRandomizer ar = new AlfaRandomizer(bc, Collections.emptyList());

    @Test
    @Ignore
    public void testAlfa2AvroRoundTrip() throws Exception {
        Employee obj = ar.random(Employee.TYPE_NAME);

        OutputStream baos = new ByteArrayOutputStream();

        // Can write direct to a stream
        AvroCodec.exportObj( CodecConfig.defaultCodecConfig(), obj, baos );

        // Or export as an Avro record
        GenericRecord gr = AvroCodec.exportToRecord(obj);
        
        System.out.println( gr );
        Assert.assertTrue( gr.get("LastName") != null );        
        Assert.assertTrue( gr.get("Salary") != null );        

        // For round tripping, read the Avro record to recreate Alfa object
        Employee decoded = AvroCodec.importObj(ccb, gr);

        // Assert what was round-tripped and original object matches
        Assert.assertEquals(obj, decoded);        
    }
}