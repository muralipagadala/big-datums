#!/bin/bash

cat bddatagen_people_wHeader_v1_5k.txt | awk 'BEGIN{FS="\t"; sum=0} {sum+=$11} END{print sum}'
