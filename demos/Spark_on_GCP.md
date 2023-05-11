Google Cloud Platform (GCP) offers a service similar to AWS EMR called Google Cloud Dataproc. It's a fast, easy-to-use, managed Spark and Hadoop service for distributed data processing.

Walk-through the steps for running a Spark job on GCP using Cloud Dataproc.

1. **Create a GCP Account and Set Up a Project**:
    - If you haven't already, create an account on https://cloud.google.com/.
    - Once you've logged in, create a new project for your Spark work from the GCP Console.

2. **Enable Necessary APIs**:
    - You need to enable the Cloud Dataproc API for your project. You can do this by navigating to APIs & Services > Library, and then search for and enable the Cloud Dataproc API.

3. **Create a Cloud Dataproc Cluster**:
    - In the GCP Console, go to the Cloud Dataproc service.
    - Click on the "Create cluster" button.
    - Fill in the necessary details like region, zone, cluster name, etc. In the "Software Configuration" section, choose Spark.
    - After reviewing your settings, click on the "Create" button to create the cluster.

4. **Submit a Spark Job**:
    - After your cluster is up and running, you can submit Spark jobs to it.
    - Navigate to your cluster's details page and click on the "Jobs" tab.
    - Click on the "Submit job" button.
    - Choose "Spark" as your job type. You can specify your Spark job's main class or main Python file. You can also specify the arguments and the location of your Spark job file.
    - After filling in all the details, click on the "Submit" button to submit your Spark job.

5. **Monitor Your Spark Job**:
    - You can monitor the status of your Spark job in the Jobs tab of your Cloud Dataproc cluster details page.
    - Google Cloud Console also provides options to monitor job and cluster metrics, which can be helpful for debugging and optimizing your Spark jobs.

