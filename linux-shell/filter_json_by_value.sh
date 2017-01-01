#!/bin/bash

cat ../data/bdPerson_v1_1k.json | jq 'select(.first_name == "Amelia")'
