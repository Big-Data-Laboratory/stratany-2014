# Description

This folder provides a sample web service to query the data loaded into Hadoop by the `dataformats` scripts.

It's very simple data services project that is built using the spring boot framework (http://projects.spring.io/spring-boot/).  To run just build the project and then `java -jar youpath/resttest-1.0-SNAPSHOT.jar` and it will start up on localhost:8080

It is set up as a Maven project. Most libraries are included as part of the `pom.xml` file, however the `hive-0.13.0-uber.jar` file located under the `lib` folder must be added manually to the Maven project. The following link provides instructions on how to set this up:
http://maven.apache.org/guides/mini/guide-3rd-party-jars-local.html

Review the configuration section to make sure you have added the details to connect your web service to the cluster.


# Prerequisites
- Run through `dataformats` example to generate data into Hadoop.
- Hadoop environment setup, Cloudera distribution recommended for Impala. CDH 5.1 was used for this example.
- Java JDK 7. JDK7u55 was used for this example.

# Configuration
- `src/main/resources/database.properties` - edit the impala and hive properties to point to the ip address of your cluster.

# Example queries
Using curl
- Hive: https://github.com/silicon-valley-data-science/stratany-2014/blob/master/resttest/queries/hive-queries.sh
- Impala: https://github.com/silicon-valley-data-science/stratany-2014/blob/master/resttest/queries/impala-queries.sh
