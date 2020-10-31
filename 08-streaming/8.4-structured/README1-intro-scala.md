<link rel='stylesheet' href='../../assets/css/main.css'/>

# Lab 8.4 - Structured Streaming 1

### Overview

Run a Spark Structured Streaming  job

### Depends On

None

### Run time

20-30 mins

## STEP 1: Go to project directory

```bash
    $    cd ~/dev/spark-labs/08-streaming/8.4-structured
```

## Step 2 : Fix TODO-1

Edit file : `src/main/scala/structured/Intro.scala`  and fix TODO-1 (only)

## Step 3: Build the project

We will use `sbt` to build the project.  

**Inspect the `build.sbt` file**

```bash
    # be in project root directory
    $  cd ~/dev/spark-labs/08-streaming/8.4-structured
    $  sbt clean package
```

Make sure there are no errors and there is output in `target` dir.

## Step 4: Run Ncat Server to simulate network traffic

Open another terminal into Spark node (terminal 1)

Use `nc` command to move text you type in terminal #2 to port 10000
Open an terminal and run this command at prompt

```bash
    $ ncat -l -k -p 10000
```

## Step 5: Run the streaming application

```bash
    # be in project root directory
    $ cd ~/dev/spark-labs/08-streaming/8.4-structured

    $   ~/apps/spark/bin/spark-submit  --master local[2]   \
        --driver-class-path logging/  \
        --class structured.Intro  \
        target/scala-2.12/structured-streaming_2.12-1.0.jar
```

Lets call this Terminal 2

Also note --master url `local[2]`

* We are using a local 'embedded' server  (quick for development)
* And we need at least 2 cpu cores -- one for receiver (long running task) and another for our program.  
If only allocated one core `local[1]`  the program will have run-time errors or won't run!


## Step 6:  Test by typing text in the terminal

In the **Netcat Terminal 1**, type some data

```text
a
b
hi
bye
```

Inspect the output from **Spark streaming on terminal 2**

Output would be similar to the following:

```console
-------------------------------------------
Batch: 0
-------------------------------------------
+-----+
|value|
+-----+
+-----+

-------------------------------------------
Batch: 1
-------------------------------------------
+-----+------+
|value| query|
+-----+------+
|    a|query1|
+-----+------+

-------------------------------------------
Batch: 2
-------------------------------------------
+-----+------+
|value| query|
+-----+------+
|    b|query1|
+-----+------+

```


Here is a sample screenshot (yours might look slightly different)

<a href="../../assets/images/8.4a-structured-streaming.png"><img src="../../assets/images/8.4a-structured-streaming.png" style="border: 5px solid grey; width:100%;"/></a>

## Step 7 - Fix TODO-2

Edit file : `src/main/scala/structured/Intro.scala`  and fix TODO-2

Build the project

```bash
    # be in project root directory
    $  cd ~/dev/spark-labs/08-streaming/8.4-structured
    $  sbt clean package
```

## Step 8 - Run the app again

**=>  Hit Ctrl+C  on terminal 2 to kill the running Spark streaming application**

```bash
# be in project  directory
$   cd ~/dev/spark-labs/08-streaming/8.4-structured

$   ~/apps/spark/bin/spark-submit  --master local[2]   \
        --driver-class-path logging/  \
        --class structured.Intro  \
        target/scala-2.12/structured-streaming_2.12-1.0.jar
```

Let's see if our filter is working.

In the Netcat Terminal type some input

```text
a
xyz
```

Inspect the output from Spark streaming on terminal 2

Output would be similar to the following:

```console
-------------------------------------------
Batch: 1
-------------------------------------------
+-----+-----+
|value|query|
+-----+-----+
+-----+-----+

+-----+------+
|value| query|
+-----+------+
|    a|query1|
+-----+------+

-------------------------------------------
Batch: 2
-------------------------------------------
-------------------------------------------
Batch: 2
-------------------------------------------
+-----+------+
|value| query|
+-----+------+
|  xyz|query2|
+-----+------+

+-----+------+
|value| query|
+-----+------+
|  xyz|query1|
+-----+------+
```

## Step 7 - `Structured Streaming` UI

Inspect `Structured Straming` tab on Spark UI.

Note the active queries running.

Inspect stats of streaming

<a href="../../assets/images/structured-steaming-1.png"><img src="../../assets/images/structured-steaming-1.png" style="border: 5px solid grey; width:100%;"/></a>

<a href="../../assets/images/structured-steaming-2.png"><img src="../../assets/images/structured-steaming-2.png" style="border: 5px solid grey; width:100%;"/></a>

## Step 8 - End Streaming App

**=>  Hit Ctrl+C  on terminal #1 to kill Spark streaming application**
