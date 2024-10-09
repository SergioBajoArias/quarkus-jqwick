FROM registry.access.redhat.com/ubi8/ubi-minimal:8.10

# Install required packages
RUN microdnf install git tar -y

# Install compiler dependencies
RUN microdnf install gcc glibc-devel zlib-devel libstdc++-static -y

# Install Docker
RUN microdnf -y install yum
RUN yum install -y yum-utils
RUN yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
RUN yum install -y docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin

# Install GraalVM JDK
RUN curl https://download.oracle.com/graalvm/21/latest/graalvm-jdk-21_linux-x64_bin.tar.gz --output graalvm-jdk-21_linux-x64_bin.tar.gz
RUN ls
RUN tar -xzvf graalvm-jdk-21_linux-x64_bin.tar.gz
ENV JAVA_HOME=/IdeaProjects/quarkus-jqwick/graalvm-jdk-21.0.4+8.1
ENV PATH=$JAVA_HOME/bin:$PATH


# Set `DEVCONTAINER` environment variable to help with orientation
ENV DEVCONTAINER=true