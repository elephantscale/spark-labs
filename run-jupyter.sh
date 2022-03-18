#!/bin/bash

## if you need to set anaconda path explicitly, do so here
## how ever, if you are using 'custom python environment', 
## leave this line commented out (do not over-ride the default path)

#export PATH=$HOME/anaconda3/bin:$PATH
export SPARK_HOME=$HOME/spark
jupyter lab

