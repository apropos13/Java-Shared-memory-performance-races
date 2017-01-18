#!/bin/bash

#Second argument must be the state!
#Script to calc average. Dumps stderr to dump before piping the result to grep
./runTests.sh $1 2> dump | grep -e '^Threads' | awk '{ total += $3; count++ } END { print total/count }' 

rm dump
