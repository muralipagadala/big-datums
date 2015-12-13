#!/bin/bash

#assuming current working dir is big-datums/big-data/hive/scripts/create
hadoop fs -mkdir /people_v1
hadoop fs -put ../../../../data/bddatagen_people_wHeader_v1_5k.txt /people_v1/
hive -f ../../ddl/people_v1.hql

