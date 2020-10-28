package structured


import org.apache.spark.sql.functions._
import org.apache.spark.sql.SparkSession


/*
$  spark-submit  --master local[2]   --driver-class-path logging/  --class x.StructuredStreaming  target/scala-2.11/structured-streaming_2.11-1.0.jar
 */


object WordCount {
  def main(args: Array[String]) {

    val spark = SparkSession.builder.appName("Structured Streaming Word Count").
                getOrCreate()
    import spark.implicits._

    val lines = spark.readStream.format("socket").
                      option("host", "localhost").
                      option("port", 10000)
                      .load()

    lines.printSchema
    
    // Split the lines into words
    val words = lines.as[String].flatMap(_.split(" "))
      
    // Generate running word count
    val wordCounts = words.groupBy("value").count()

    val query = wordCounts.writeStream
                  .outputMode("complete")
                  .format("console")
                  .start()

    query.awaitTermination()

  }
}
