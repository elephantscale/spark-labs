#!/bin/bash

export PATH=$HOME/anaconda3/bin:$PATH
export SPARK_HOME=$HOME/spark
jupyter lab

## --- older approach ---
# export PYSPARK_PYTHON=$(which python3)
# export PYSPARK_DRIVER_PYTHON=$(which jupyter)
# export PYSPARK_DRIVER_PYTHON_OPTS="notebook"

## pyspark i local mode
# $SPARK_HOME/bin/pyspark

## pyspark connect to cluster
#$SPARK_HOME/bin/pyspark --master spark://localhost:7077  --executor-memory 4g --driver-memory 1g
