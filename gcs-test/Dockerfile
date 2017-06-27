# FROM frolvlad/alpine-oraclejdk8:slim
FROM openjdk:8

# COPY gcsfuse.repo /etc/yum.repos.d/
# RUN dnf -y install gcsfuse

RUN mkdir -p /gcs/app_folder

RUN export CLOUD_SDK_REPO="cloud-sdk-$(lsb_release -c -s)"
RUN export GCSFUSE_REPO="gcsfuse-$(lsb_release -c -s)"

RUN echo "deb http://packages.cloud.google.com/apt $GCSFUSE_REPO main" | sudo tee /etc/apt/sources.list.d/gcsfuse.list
RUN curl https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo apt-key add -

RUN apt-get -y update
RUN apt-get -y install --no-install-recommends google-cloud-sdk
RUN apt-get -y install --no-install-recommends gcsfuse

VOLUME /tmp
ADD target/gcs-test-0.0.1-SNAPSHOT.jar app.jar
# RUN sh -c 'touch /app.jar'
EXPOSE 8080
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
