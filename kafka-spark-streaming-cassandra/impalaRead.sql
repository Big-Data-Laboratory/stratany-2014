create EXTERNAL TABLE wc LIKE PARQUET '/tmp/wc/rdd=10/_metadata' PARTITIONED BY (rdd int) stored as parquet LOCATION '/tmp/wc';
alter table wc add partition (rdd=10);
select key,value from wc order by value desc limit 50;

