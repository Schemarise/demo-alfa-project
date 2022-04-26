package com.acme.model.spark

import java.nio.file.Files
import java.util

import org.scalatest.FunSuite
import alfa.rt.codec.spark.SparkCodec
import alfa.rt.utils.AlfaRandomizer
import org.apache.spark.sql.{Row, SparkSession}
import acme.model.Employee
import alfa.rt.{BuilderConfig, IBuilderConfig}

class AcmeSparkTest extends FunSuite {
  private val codec = new SparkCodec
  private val bc = BuilderConfig.newBuilder.setShouldValidateOnBuild(false).build
  private val ar = new AlfaRandomizer(bc)

  // Create a ALFA object object instance and convert it to a Spark Row object
  test("ALFA object to Spark Row") {

    // Create a randomised Employee object
    val emp = ar.random[Employee](Employee.TYPE_NAME)

    // Export/flatten the ALFA object into a Spark Row object
    val row = codec.exportToRow(emp)

    // Print the row object contents
    val fns = row.schema.fieldNames.toSet
    fns.foreach( f => {
      val i = row.schema.fieldIndex(f)
      val v = row.get(i)
      println( s"   $f = $v" )
    })

    // Validate and display Spark row schema 
    assert( fns.contains("LastName") )
    assert( fns.contains("Salary") )
    println("Row Schema: " + row.schema)
  }

  // Create a list ALFA objects, convert them to Spark Rows, then into a DataFrame
  test("ALFA objects to DataFrame") {
    val spark = SparkSession.builder
      .master("local[*]")
      .config("spark.driver.bindAddress", "127.0.0.1")
      .config("spark.ui.enabled", false)
      .appName("AlfaSparkTest")
      .getOrCreate()

    spark.sparkContext.setLogLevel("WARN")

    val rows = new util.ArrayList[Row]()
    
    // Lets create 20 randomized Employee objects and place them in a list of Spark row objects
    Range.apply(0, 20).map( _ => {
      val d = ar.random[Employee](Employee.TYPE_NAME)
      rows.add(codec.exportToRow(d))
    } )

    // Create a Spark DataFrame using the Rows created off the ALFA objects
    val df = spark.createDataFrame( rows, codec.toSchema(Employee.TYPE_NAME) ).toDF()
    assert( df.count() == 20 )

    // Write the contents, and print the dataframe
    val f = Files.createTempDirectory("alfa-spark-parquet")
    df.write.parquet(f.toString + "/data")
    
    df.show(10)
  }
}
