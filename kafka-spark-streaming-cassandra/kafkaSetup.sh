#!/bin/bash
kafka-topics.sh --create --topic demo --zookeeper localhost:2181 --partitions 1 --replication-factor 1
