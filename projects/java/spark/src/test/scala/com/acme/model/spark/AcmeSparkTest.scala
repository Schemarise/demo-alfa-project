package com.acme.model.spark

import java.nio.file.Files
import java.util
import org.scalatest.FunSuite
import alfa.rt.codec.spark.SparkCodec
import alfa.rt.utils.AlfaRandomizer
import org.apache.spark.sql.{Row, SparkSession}

import acme.model.Employee

class AcmeSparkTest extends FunSuite {
  private val codec = new SparkCodec
  private val ar = new AlfaRandomizer

  // Create a ALFA object object instance and convert it to a Spark Row object
  test("ALFA object to Spark Row") {
    val emp = ar.random[Employee](Employee.TYPE_NAME)
    val row = codec.exportToRow(emp)

    val fns = row.schema.fieldNames.toSet
    fns.foreach( f => {
      val i = row.schema.fieldIndex(f)
      val v = row.get(i)
      println( s"   $f = $v" )
    })

    assert( fns.contains("LastName") )
    assert( fns.contains("Salary") )
    println("Row Schema: " + row.schema)
  }

  // Create a list ALFA objects, convert them to Spark Rows, then into a DataFrame
  test("ALFA objects to DataFrame") {
    val spark = SparkSession.builder
      .master("local")
      .config("spark.ui.enabled", false)
      .appName("AlfaSparkTest")
      .getOrCreate()

    spark.sparkContext.setLogLevel("WARN")

    val rows = new util.ArrayList[Row]()
    
    Range.apply(0, 20).map( _ => {
      val d = ar.random[Employee](Employee.TYPE_NAME)
      rows.add(codec.exportToRow(d))
    } )

    val df = spark.createDataFrame( rows, codec.toSchema(Employee.TYPE_NAME) ).toDF()
    assert( df.count() == 20 )

    val f = Files.createTempDirectory("alfa-spark-parquet")
    df.write.parquet(f.toString + "/data")
    
    df.show(10)
  }
}
