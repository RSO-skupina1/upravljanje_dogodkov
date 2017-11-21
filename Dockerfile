FROM openjdk:8
MAINTAINER kustrun

COPY $TRAVIS_BUILD_DIR/target target
WORKDIR $TRAVIS_BUILD_DIR/

EXPOSE 8081
CMD PORT=8081 java -cp target/classes:target/dependency/* com.kumuluz.ee.EeApplication

