<link rel='stylesheet' href='../../assets/css/main.css'/>

# Lab - Structured Streaming 2 - WordCount

### Overview

Run a Spark Structured Streaming  job - do a running wordcount

### Depends On

None

### Run time

20-30 mins

## STEP 1: Go to project directory

```bash
    $    cd ~/dev/spark-labs/08-streaming/8.4-structured
```

## Step 2 : Inspect file

Inspect file : [src/main/scala/structured/WordCount.scala](src/main/scala/structured/WordCount.scala)

Complete TODO items, if any

## Step 3: Build the project

```bash
    # be in project root directory
    $  cd ~/dev/spark-labs/08-streaming/8.4-structured
    $  sbt clean package
```

Make sure there are no errors and there is output in `target` dir.

## Step 4: Run Netcat Server to simulate network traffic

Open another terminal into Spark node (terminal #2)

Use `nc` command to move text you type in terminal #2 to port 10000
Open an terminal and run this command at prompt

```bash
    $ nc -lk 10000

    # if this gives an error like 'Protocol not available' use this
    # $  ~/bin/nc  -lk 10000
```


## Step 5: Run the streaming application

```bash
    # be in project root directory
    $   cd ~/dev/spark-labs/08-streaming/8.4-structured

    $  ~/apps/spark/bin/spark-submit  --master local[2]   --driver-class-path logging/  --class structured.WordCount  target/scala-2.12/structured-streaming_2.12-1.0.jar 
```


## Step 6: Provide Input

In the netcat window, type some sample text

```text
hi scala
hi python
```

## Step 7 - Observe the streaming output

Inspect the output in spark streaming window

It may look like below

```text
-------------------------------------------
Batch: 1
-------------------------------------------
+-----+-----+
|value|count|
+-----+-----+
|scala|    1|
|   hi|    1|
+-----+-----+

-------------------------------------------
Batch: 2
-------------------------------------------
+------+-----+
| value|count|
+------+-----+
| scala|    1|
|    hi|    2|
|python|    1|
+------+-----+
```

## Step 8 - Structured Streaming UI

Inspect `Structured Straming` tab on Spark UI

Inspect stats of streaming

<a href="../../assets/images/structured-steaming-1.png"><img src="../../assets/images/structured-steaming-1.png" style="border: 5px solid grey; width:100%;"/></a>

<a href="../../assets/images/structured-steaming-2.png"><img src="../../assets/images/structured-steaming-2.png" style="border: 5px solid grey; width:100%;"/></a>

## Step 9 - End Streaming App

**=>  Hit Ctrl+C  on terminal #1 to kill Spark streaming application**
