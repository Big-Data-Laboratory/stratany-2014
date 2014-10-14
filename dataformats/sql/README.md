# Description

The files under this directory have the SQL queries that can be run in Hive and Impala to create the tables and insert data in text, sequence, and parquet format for the two datasets listed under the `data` folder. Prior to running these scripts, you need to first load the data using the `bin/loadata_into_hdfs.sh` script:

- `create_database.sql`: Creates the stratany2014 DB where the tables will reside.
- `strata_wide_table_text.sql`: Creates the external table schema for the widerow dataset in text file format. Note the location of the external table points to where the file was loaded in the load data shell script.
- `strata_wide_table_parquet.sql`: Creates the table schema for the widerow dataset in parquet columnar format.
- `strata_wide-table_parquet_insert.sql`: Inserts the data from the widerow table in text file format to the one in parquet format.
- `strata_wide_table_seq.sql`: Creates the table schema for the widerow dataset in sequence file format.
- `strata_wide-table_parquet_seq.sql`: Inserts the data from the widerow table in text file format to the one in sequence format.
- `drwho_text.sql`: Creates the external table schema for the drwho dataset in text file format. Note the location of the external table points to where the file was loaded in the load data shell script.
- `drwho_parquet.sql`: Creates the table schema for the drwho dataset in parquet columnar format.
- `drwho_parquet_insert.sql`: Inserts the data from the drwho table in text file format to the one in parquet format.


# Prerequisites
- Hadoop environment needed to run, in particular CDH for Impala examples. Tested under CDH 5.1.2 using parcels.
- JDK 7. Tested with JDK 1.7u55
