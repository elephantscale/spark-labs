<link rel='stylesheet' href='../../assets/css/main.css'/>


# Lab - Structured Streaming 2 - Word Count


### Overview

Doing wordcount using Spark Streaming

### Depends On

None

### Run time

20-30 mins


## STEP 1: Go to project directory

```bash
$ cd ~/dev/spark-labs/08-streaming/8.4-structured/python
```

## Step 2 : Inspect WordCount.scala

Inspect file : `src/main/scala/structured/WordCount.scala`

Let's just run the file as is for now.

It read input lines and breaks them into words.

## Step 3: Build the project

```bash
    # be in project root directory
    $  cd ~/dev/spark-labs/08-streaming/8.4-structured
    $  sbt clean package
```


## Step 4: Run Netcat Server to simulate network traffic (terminal 1)

(Or leave the previous one running)

```bash
$   ncat -l -k -p 10000

# if this shows 'Port already in use', get the process is and kill the process
$ sudo netstat -plnt | grep 10000
# Process id will be shown in output
$ sudo kill -9 <process id>
```

## Step 5: Run the streaming application (Terminal 2)

```bash
# be in project root directory
$   cd ~/dev/spark-labs/08-streaming/8.4-structured

$   $SPARK_HOME/bin/spark-submit  --master local[2]   --driver-class-path logging/  \
       --class structured.WordCount     target/scala-2.12/structured-streaming_2.12-1.0.jar 
```

## Step 6:  Enter some data (in ncat terminal 1)

In the Terminal 1, input some data, like below

```text
a
b
a b
b    c
hi scala
```

Inspect the output from Spark streaming on terminal 2

Output would be similar to the following (yours might be different)

```text
-------------------------------------------
Batch: 1
-------------------------------------------
+--------+------------+
|   value|       query|
+--------+------------+
|       a|query1-lines|
|     a b|query1-lines|
|hi scala|query1-lines|
|       b|query1-lines|
|  b    c|query1-lines|
+--------+------------+

+-----+------------+
| word|       query|
+-----+------------+
|    a|query2-words|
|    a|query2-words|
|    b|query2-words|
|   hi|query2-words|
|scala|query2-words|
|    b|query2-words|
|    b|query2-words|
|    c|query2-words|
+-----+------------+

```

## Step 7 - Fix TODO-1

Edit file : `src/main/scala/structured/WordCount.scala`

Uncomment the code block around TODO-1.

And fix TODO-1 items

## Step 8: Build the project

```bash
    # be in project root directory
    $  cd ~/dev/spark-labs/08-streaming/8.4-structured
    $  sbt clean package
```

## Step 9: Run the streaming application (Terminal 2)

```bash
# be in project root directory
$   cd ~/dev/spark-labs/08-streaming/8.4-structured

$   $SPARK_HOME/bin/spark-submit  --master local[2]   --driver-class-path logging/  \
       --class structured.WordCount     target/scala-2.12/structured-streaming_2.12-1.0.jar 
```

## Step 10:  Enter some data (in ncat terminal 1)

In the Terminal 1, input some data, like below

```text
a
b
a b
b    c
hi scala
hi spark
```

Inspect the output on streaming terminal 2.

**Q==>Is our wordcount working?**

```console
-------------------------------------------
Batch: 1
-------------------------------------------
+--------+------------+
|   value|       query|
+--------+------------+
|hi spark|query1-lines|
+--------+------------+

+-----+------------+
| word|       query|
+-----+------------+
|   hi|query2-words|
|spark|query2-words|
+-----+------------+

+-----+-----+-----------------+
| word|count|            query|
+-----+-----+-----------------+
|scala|    1|query3-wordcounts|
|    c|    1|query3-wordcounts|
|   hi|    1|query3-wordcounts|
|    b|    3|query3-wordcounts|
|    a|    2|query3-wordcounts|
+-----+-----+-----------------+
```

## Step 11 - `Structured Streaming` UI

Inspect `Structured Straming` tab on Spark UI.

Note the active queries running.

Inspect stats of streaming.

<a href="../../assets/images/structured-steaming-1.png"><img src="../../assets/images/structured-steaming-1.png" style="border: 5px solid grey; width:100%;"/></a>

<a href="../../assets/images/structured-steaming-2.png"><img src="../../assets/images/structured-steaming-2.png" style="border: 5px solid grey; width:100%;"/></a>

## Step 12 - End Streaming App

**=>  Hit Ctrl+C  on terminal #1 to kill Spark streaming application**
