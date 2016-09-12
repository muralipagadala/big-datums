#!/bin/bash

#set start and end dates in specified format
start_dt=`date +"%Y-%m-%d" -d "2020-01-01"`
end_dt=`date +"%Y-%m-%d" -d "2021-01-01"`

#use $current_dt in loop
current_dt=$start_dt

#loop over dates in range and print
while [ $current_dt != $end_dt ];
do 
  echo $current_dt;
  
  #add 1 day to current_dt
  current_dt=`date +"%Y-%m-%d" -d "$current_dt 1 day"`;
done
