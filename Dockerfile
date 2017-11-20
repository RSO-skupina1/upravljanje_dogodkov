FROM openjdk:8
MAINTAINER kustrun

COPY ./target target
WORKDIR /

EXPOSE 8081
CMD PORT=8081 java -cp target/classes:target/dependency/* com.kumuluz.ee.EeApplication

