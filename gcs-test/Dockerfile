FROM frolvlad/alpine-oraclejdk8:slim

COPY gcsfuse.repo /etc/yum.repos.d/
RUN dnf -y install gcsfuse
RUN mkdir -p /etc/app_folder

VOLUME /tmp
ADD target/gcs-test-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
