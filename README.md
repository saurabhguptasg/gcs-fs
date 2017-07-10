# gcs-fs

### Create a bucket

1. Launch Google Cloud console and navigate over to the Cloud Storage area.

2. Create a bucket with a unique name, e.g. `my-unique-bucket-name`

3. Add a few test files to this bucket

### Build the System

1. Launch Google Cloud Shell, then update your Cloud Shell to use JDK 8 (this will be needed to successfully build the Spring Boot app):
    ```
    sudo update-java-alternatives -s java-1.8.0-openjdk-amd64 && export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/jre
    ```

2. Clone repo in cloud shell (assuming this repo is sync'ed with Google Cloud repository):
    ```
    gcloud source repos clone gcs-fs
    ```

    If the repo is not sync'ed, then you can directly clone it into your Cloud Shell:
    
    ```
    git clone git@github.com:saurabhguptasg/gcs-fs.git
    ```
    
3. then change into the correct folder and run maven:
    ```
    cd gcs-fs/gcs-test
    mvn clean package
    ```

4. the `gcs-test` folder contains the `Dockerfile` and other assets that are
needed to build the system. We'll submit this build to Google Cloud Container Builder
to build and store the image in the Container Registry
    ```
    gcloud container builds submit --tag gcr.io/[PROJECT-ID]/[IMAGE] .
    ```
    in the above line, substitute the `[PROJECT-ID]` and `[IMAGE]` values as appropriate.

 ### Create GKE Cluster & Deploy
 
 1. Spin up a GKE cluster from the Cloud console, giving it a certain name, e.g. `cluster-1`
 
 2. In order to point the `kubectl` in the shell to the cluster just created, get the cluster credentials,
 filling in the appropriate values for cluster name, cluster zone and project id in the following command:
 
     ```
     gcloud container clusters get-credentials [cluster-name] --zone [cluster-zone] --project [PROJECT-ID]
     ```
 
 3. update the `gcs-fs-pod.yaml` file with appropriate information, changing the `my-unique-bucket-name` to your exact bucket name.
 NOTE: you cannot use a folder name, the GCS Fuse mount will only work at the entire bucket level. Also, do not change
 the target mount point of the folder (currently `/gcs/app_folder`) since the java app is looking for that exact
 location; you can change it, but then you'll have to update the java app and repackage in the above step.
 
 4. deploy the updated yaml file to the cluster:
     ````
     kubectl create -f gcs-fs-pod.yaml
     ````
 5. once deplyment is complete, check the services running with the following command:
     ````
     kubectl get services
     ````
     note the external ip address of the `gcs-fs-servce`
 
 6. navigate to the external `[ip address]/ls` of the `gcs-fs-service` to see the list of files in the bucket that
 had been targeted in the pod yaml. You can then test by adding more files to the bucket and refreshing
 the browser to see the new file listed in the returned json data:
     ````
     http://[external_ip_address]/ls
     ````
