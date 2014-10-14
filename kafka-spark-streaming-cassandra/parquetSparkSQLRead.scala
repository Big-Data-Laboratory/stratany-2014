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
//may need to add imports from sparkDemo.scala
val sqlContext = new org.apache.spark.sql.SQLContext(sc)
import sqlContext.createSchemaRDD
val parquetFile = sqlContext.parquetFile("hdfs://localhost:8020/tmp/wc/rdd=10")
parquetFile.registerAsTable("test")
sqlContext.sql("cache table test")
sqlContext.sql("select * from test order by value desc limit 50").foreach(println)

