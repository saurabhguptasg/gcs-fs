FROM ubuntu:22.04

RUN apt-get update
RUN apt-get install -y curl gcc apt-transport-https apt-utils lsb-release openssh-client
RUN apt-get install -y ca-certificates wget
RUN apt-get install -y gnupg

RUN export GCSFUSE_REPO=gcsfuse-`lsb_release -c -s` && \
    echo ">> >> gcs fuse is: $GCSFUSE_REPO" && \
    echo "deb https://packages.cloud.google.com/apt $GCSFUSE_REPO main" | tee /etc/apt/sources.list.d/gcsfuse.list && \
    curl https://packages.cloud.google.com/apt/doc/apt-key.gpg | apt-key add -

RUN apt-get update
RUN apt-get install -y ssh
RUN apt-get install -y gcsfuse

RUN mkdir -p /gcs
ARG BUCKET_NAME
ENV BUCKET_NAME="$BUCKET_NAME"
EXPOSE 22
ENTRYPOINT ["gcsfuse", "$BUCKET_NAME", "/gcs"]
