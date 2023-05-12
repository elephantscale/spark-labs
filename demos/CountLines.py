import sys
import time
from pyspark.sql import SparkSession

if len(sys.argv) < 2:
    sys.exit("need file(s) to load")

## TODO 1: Give a name
spark = SparkSession.builder.appName("Process Files -- Mark").getOrCreate()

print('### Spark UI available on port : ' + spark.sparkContext.uiWebUrl.split(':')[2])

for file in sys.argv[1:]:
    ## TODO-2 : read a file as dataset
    ## hint : spark.read.text(file)
    f = spark.read.text(file)

    t1 = time.perf_counter()
    ## TODO-3 : Count the number of lines
    ## Hint : count
    count = f.count()
    t2 = time.perf_counter()

    print("### {}: count:  {:,} ,  time took:  {:,.2f} ms".format(file, count, (t2-t1)*1000))

    # end of for loop

spark.stop()  # close the session