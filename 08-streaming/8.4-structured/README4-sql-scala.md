# Lab - Structured Streaming 4 - SQL


### Overview

Use Spark SQL in structured streaming

### Depends On

clickstream lab

### Run time

20-30 mins


## STEP 1: Go to project directory

```bash
$ cd ~/dev/spark-labs/08-streaming/8.4-structured/
```

## Step 2 : Inspect file `SQL.scala`

Inspect file : `src/main/scala/structured/SQL.scala`

## Step 3 - Compile and Run the file

```bash
# be in project root directory
$   cd ~/dev/spark-labs/08-streaming/8.4-structured

$   rm -f input/* ;  \
    $SPARK_HOME/bin/spark-submit  --master local[2]   --driver-class-path logging/  \
       --class structured.SQL     target/scala-2.12/structured-streaming_2.12-1.0.jar 
```

## Step 4 - Supply Input

The program is waiting to read files in `input/` directory.   Let's add some files.

```bash
$   cd   ~/dev/spark-labs/08-streaming/8.4-structured
$   cp   clickstream.json   input/1.json
```
Watch the output.

It would look something like this:

```console
-------------------------------------------
Batch: 0
-------------------------------------------
+-------+-----------+----+-----------------+----+----------+-------------+------+------------------+
| action|   campaign|cost|           domain|  ip|   session|    timestamp|  user|             query|
+-------+-----------+----+-----------------+----+----------+-------------+------+------------------+
|clicked|campaign_19| 118|      youtube.com|ip_4|session_36|1420070400000|user_9|query1-clickstream|
|blocked|campaign_12|   5|     facebook.com|ip_3|session_96|1420070400864|user_5|query1-clickstream|
|clicked| campaign_3|  54|sf.craigslist.org|ip_9|session_61|1420070401728|user_8|query1-clickstream|
| viewed|campaign_20| 133|       google.com|ip_9|session_69|1420070416416|user_7|query1-clickstream|
+-------+-----------+----+-----------------+----+----------+-------------+------+------------------+

```

## Step 5: Complete TODOs

Edit file : `src/main/scala/structured/SQL.scala`

Complete TODO items


## Step 6: Run the streaming application

```bash
# be in project root directory
$   cd ~/dev/spark-labs/08-streaming/8.4-structured

$   rm -f input/* ;  \
    $SPARK_HOME/bin/spark-submit  --master local[2]   --driver-class-path logging/  \
       --class structured.SQL     target/scala-2.12/structured-streaming_2.12-1.0.jar 
```

Note : `rm -f input/*`  is used to clear the input directory

Leave this terminal running (we will call it Spark terminal)

## Step 7 - Provide Input

Open another terminal and issue the following commands.

```bash
$   cd  ~/dev/spark-labs/08-streaming/8.4-structured/
$   cp  clickstream.json input/1.json
```

In Spark terminal you should see the first batch output.

And you should also see the result of SQL query.


```console
-------------------------------------------
Batch: 0
-------------------------------------------
+-------+-----------+----+-----------------+----+----------+-------------+------+
| action|   campaign|cost|           domain|  ip|   session|    timestamp|  user|
+-------+-----------+----+-----------------+----+----------+-------------+------+
|clicked|campaign_19| 118|      youtube.com|ip_4|session_36|1420070400000|user_9|
|blocked|campaign_12|   5|     facebook.com|ip_3|session_96|1420070400864|user_5|
|clicked| campaign_3|  54|sf.craigslist.org|ip_9|session_61|1420070401728|user_8|
|blocked|campaign_18| 110|    wikipedia.org|ip_5|session_55|1420070402592|user_6|
|clicked| campaign_6|  15|comedycentral.com|ip_9|session_49|1420070403456|user_4|
|blocked| campaign_9| 139|          cnn.com|ip_8|session_13|1420070404320|user_7|
....


+-----------------+------+-----+
|           domain|visits|spend|
+-----------------+------+-----+
|    wikipedia.org|     3|  301|
|      youtube.com|     2|  125|
|          cnn.com|     1|  139|
|       sfgate.com|     1|   74|
|   funnyordie.com|     1|  171|
+-----------------+------+-----+

```

## Step 8 - Provide more input

Observe the output.

You will see the `visits` change


```bash
$   cp   clickstream.json  input/2.json
$   cp   clickstream.json  input/3.json
```

## Step 9 : Terminate the app

**=>  Hit Ctrl+C  on terminal #1 to kill Spark streaming application**


## BONUS Step : Modify the Query

Come up with another query and check the results
