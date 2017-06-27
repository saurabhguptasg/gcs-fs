# FROM frolvlad/alpine-oraclejdk8:slim

FROM openjdk:8

COPY gcsfuse.repo /etc/yum.repos.d/
# RUN dnf -y install gcsfuse

ENV GCSFUSE_REPO=gcsfuse-jessie
RUN   apt-get update && \
      apt-get -y install sudo && \
      apt-get install -y curl ca-certificates && \
      apt-get -y install --no-install-recommends google-cloud-sdk && \
      echo "deb http://packages.cloud.google.com/apt $GCSFUSE_REPO main" > /etc/apt/sources.list.d/gcsfuse.list && \
      curl https://packages.cloud.google.com/apt/doc/apt-key.gpg | apt-key add - && \
      apt-get update && \
      apt-get install -y gcsfuse wget && \
      apt-get remove -y curl --purge && \
      apt-get autoremove -y && \
      rm -rf /var/lib/apt/lists/*


RUN mkdir -p /gcs/app_folder

VOLUME /tmp
ADD target/gcs-test-0.0.1-SNAPSHOT.jar app.jar
# RUN sh -c 'touch /app.jar'
EXPOSE 8080
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
