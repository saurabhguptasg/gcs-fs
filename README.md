# gcs-fs

update your Cloud Shell to use JDK 8:

    sudo update-java-alternatives -s java-1.8.0-openjdk-amd64 && export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/jre

clone repo in cloud shell:

    gcloud source repos clone gcs-fs 
    
then change into the correct folder and run maven:

    cd gcs-fs/gcs-test
    mvn clean package

the `gcs-test` folder contains the `Dockerfile` and other assets that are
needed to build the system. We'll submit this build to Google Cloud Container Builder
to build and store the image in the Container Registry

    gcloud container builds submit --tag gcr.io/[PROJECT-ID]/[IMAGE] .

in the above line, substitute the `[PROJECT-ID]` and `[IMAGE]` values as appropriate.

