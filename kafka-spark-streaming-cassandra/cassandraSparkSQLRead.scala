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
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
val sqlContext = new org.apache.spark.sql.SQLContext(sc)
import sqlContext.createSchemaRDD
val conf = new SparkConf()
conf.set("cassandra.connection.host", "localhost")
import com.datastax.bdp.spark.SparkContextCassandraFunctions._
val sc = new SparkContext("local", "Cassandra Connector Test", conf)
case class W(key: String, value: Int)
sc.cassandraTable[W]("test", "word").registerAsTable("word")
sqlContext.sql("cache table word")
sqlContext.sql("select key,value from word order by value desc limit 50").take(50)
