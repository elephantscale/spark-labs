<link rel='stylesheet' href='../../assets/css/main.css'/>


# Lab 8.4 - Structured Streaming 1

### Overview

Run a Spark Structured Streaming  job

### Depends On

None

### Run time

30-40 mins


## STEP 1: Go to project directory

```bash
    $    cd ~/dev/spark-labs/08-streaming/8.4-structured
```


## Step 2 : Fix TODO-1 & 2

Edit file : `src/main/scala/x/SS1_Intro.scala`


## Step 3: Build the project

We will use `sbt` to build the project.  

**Inspect the `build.sbt` file**

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
    $ cd ~/dev/spark-labs/08-streaming/8.4-structured

    $   ~/apps/spark/bin/spark-submit  --master local[2]   \
        --driver-class-path logging/  \
        --class x.SS1_Intro  \
        target/scala-2.12/structured-streaming_2.12-1.0.jar 
```

Lets call this Terminal #1

Also note --master url `local[2]`

* We are using a local 'embedded' server  (quick for development)
* And we need at least 2 cpu cores -- one for receiver (long running task) and another for our program.  
If only allocated one core `local[1]`  the program will have run-time errors or won't run!


## Step 6:  Test by typing text in the terminal

In the Terminal #2, copy and paste the following lines (these are lines from our clickstream data)

```text
a
abc
Hello world
```

Inspect the output from Spark streaming on terminal #1

You should see something similar to this screen shot.
(Click on the image for larger version)   

<a href="../../assets/images/8.4a-structured-streaming.png"><img src="../../assets/images/8.4a-structured-streaming.png" style="border: 5px solid grey; width:100%;"/></a>

## Step 7 - `Structured Streaming` UI

Inspect `Structured Straming` tab on Spark UI

Inspect stats of streaming

<a href="../../assets/images/structured-steaming-1.png"><img src="../../assets/images/structured-steaming-1.png" style="border: 5px solid grey; width:100%;"/></a>

<a href="../../assets/images/structured-steaming-2.png"><img src="../../assets/images/structured-steaming-2.png" style="border: 5px solid grey; width:100%;"/></a>

## Step 8 - End Streaming App

**=>  Hit Ctrl+C  on terminal #1 to kill Spark streaming application**
