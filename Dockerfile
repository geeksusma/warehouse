FROM bellsoft/liberica-openjdk-alpine-musl:17
MAINTAINER geeksusma
COPY target/warehouse-0.0.1-SNAPSHOT.jar warehouse-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/warehouse-0.0.1-SNAPSHOT.jar"]