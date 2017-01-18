#!/bin/bash

javac UnsafeMemory.java

n_threads=1
while [ $n_threads -le 32 ]; do
    echo "Number of threads = $n_threads"
    n_operations=100
    while [ $n_operations -le 105 ]; do
	echo "Number of operations = $n_operations"
	t_time= java UnsafeMemory $1 $n_threads $n_operations 6 5 6 3 0 3
	n_operations=$((n_operations + 1))
    done
    
    n_threads=$((n_threads + 7))
done



