#!/bin/bash

echo  "printing path of executing script"
realpath $0

echo "printing directory of executing script"
dirname `realpath $0`
