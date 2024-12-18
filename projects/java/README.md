<!-- 
    If viewing raw file in GitPod, for easier reading, right-click on the file on Explorer, and select 'Open Preview' 
/-->

[![Built using ALFA](https://alfa-lang.io/_images/built-using-ALFA.png)](https://alfa-lang.io)

# ALFA-based Java Sample Projects

There are 5 sample Java projects in `projects/java` showing how the same ALFA data model can be used in different implementations.
For clarity these examples use one technology per example, however they can be made to work together, e.g. Jdbc and Avro, Spark and Rest.

To build these projects, open a new Terminal panel, if one is not open already, and type `cd projects/java` and run `mvn install`.

## Basic
This project generates the ALFA model to Java along with the testcase. A hand-written test is included along with an implementation of the `CustomerSvc` service.

## REST
This project depends on the Basic project, and exposes the [service](https://alfa-lang.io/lang/constructs/service.html) in the example model as a Swagger REST endpoint. 
  - Run this project using `mvn org.springframework.boot:spring-boot-maven-plugin:run`
  - Once started Apache Tomcat will start Swagger on port 8080. Navigate to `https://<8080- your gitpod/github host url>/swagger-ui.html`
  - Try executing `getByType` or `getAllCustomers` methods to see the random data returned from the implementation in `java/basic/src/main/java/com/acme/model/CustomerSvcImpl.java`

## Spark
This project showcases ALFA model based objects can be converted into Apache Spark DataFrames along with a conformant Spark Schema. Once the DataFrame is created,
it is saved as Parquet.
  - Run `mvn clean install` in the `projects/java/spark` directory.
  - Look at the 2 Scala tests in `projects/java/spark/src/test/scala/com/acme/model/spark/AcmeSparkTest.scala`
  - Observe how ALFA `SparkCodec` is used to convert ALFA objects into Spark Rows and then combine them to create a Spark DataFrame.

## Avro
This project shows how ALFA objects can be converted into Apache Avro records. 
  - Run `mvn clean install` in the `projects/java/avro` directory.
  - The single test demonstrates how an object is converted - ALFA > Avro > ALFA, to show how ALFA objects can be transported using Avro. 

## JDBC
Coming soon
