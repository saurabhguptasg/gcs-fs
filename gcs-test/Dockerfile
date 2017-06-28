FROM openjdk:8

# COPY gcsfuse.repo /etc/yum.repos.d/
# RUN dnf -y install gcsfuse

ENV CLOUD_SDK_VERSION 160.0.0
ENV RELEASE_VERSION jessie
RUN apt-get update && apt-get install -qqy curl gcc python-dev python-setuptools apt-transport-https lsb-release openssh-client && \
    apt-get -y install curl ca-certificates && \
    easy_install -U pip && \
    pip install -U crcmod   && \
    export CLOUD_SDK_REPO="cloud-sdk-$RELEASE_VERSION" && \
    export GCSFUSE_REPO="gcsfuse-$RELEASE_VERSION" && \
    echo "deb https://packages.cloud.google.com/apt $CLOUD_SDK_REPO main" > /etc/apt/sources.list.d/google-cloud-sdk.list && \
    echo "deb http://packages.cloud.google.com/apt $GCSFUSE_REPO main" > /etc/apt/sources.list.d/gcsfuse.list && \
    curl https://packages.cloud.google.com/apt/doc/apt-key.gpg | apt-key add - && \
    apt-get update && \
    apt-get install -y google-cloud-sdk \
        google-cloud-sdk-app-engine-python \
        google-cloud-sdk-app-engine-java \
        google-cloud-sdk-app-engine-go \
        google-cloud-sdk-datalab \
        google-cloud-sdk-datastore-emulator \
        google-cloud-sdk-pubsub-emulator \
        google-cloud-sdk-bigtable-emulator \
        kubectl && \
    apt-get install -y gcsfuse wget && \
    gcloud config set core/disable_usage_reporting true && \
    gcloud config set component_manager/disable_update_check true && \
    gcloud config set metrics/environment github_docker_image && \
    apt-get remove -y curl --purge && \
    apt-get autoremove -y && \
    rm -rf /var/lib/apt/lists/*

VOLUME ["/root/.config"]

RUN mkdir -p /gcs/app_folder

VOLUME /tmp
ADD target/gcs-test-0.0.1-SNAPSHOT.jar app.jar
# RUN sh -c 'touch /app.jar'
EXPOSE 8080
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
