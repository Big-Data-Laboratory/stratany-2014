/*
 * Copyright 2014 Silicon Valley Data Science.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import com.datastax.spark.connector._
import com.datastax.spark.connector.streaming._
import com.datastax.bdp.spark.SparkContextCassandraFunctions._
import com.datastax.bdp.spark.RDDCassandraFunctions._
 
//Change logging level to WARN
import org.apache.log4j.Logger
import org.apache.log4j.Level
Logger.getLogger("org").setLevel(Level.WARN)
Logger.getLogger("akka").setLevel(Level.WARN)
 
//Add Cassandra connection configuration to spark context
val conf = new SparkConf()
conf.set("cassandra.connection.host", "localhost")
val sc = new SparkContext(conf)
 
//Create a Streaming Context with batch interval of 60 seconds
val ssc = new StreamingContext(sc, Seconds(60))
 
//Add spark sql context
val sqlContext = new org.apache.spark.sql.SQLContext(sc)
import sqlContext._
 
//Create input stream from kafka
val lines = KafkaUtils.createStream(ssc, "localhost:2181", "demo", Map("demo" -> 10)).map(_._2)
//Split each line into words
val words = lines.flatMap(_.split(" "))
 
//Count each word in each batch
val pairs = words.map(word => (word, 1))
val wordCounts = pairs.reduceByKey(_ + _)
 
//Case class is used to assign column names
case class W(key: String, value: Int)
 
//Iterate over RDDs in batch - rdd ids are used to partition for simple example, but normally data would be partitioned by time
wordCounts.foreachRDD(rdd => {
          //Re-partition data to balance cassandra load
          val rdd2 = rdd.repartition(8)
          //Load data into Cassandra
          rdd2.saveToCassandra("test", "word", Seq("key", "value"))
          //Load data into parquet files partitioned by rdd
          val rdd3 = rdd2.map(b => W(b._1,b._2))
          rdd3.saveAsParquetFile("hdfs://localhost:8020/tmp/wc/rdd=" + rdd3.id)
})
 
//Start streaming process
ssc.start()
ssc.awaitTermination()

