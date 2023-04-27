FROM 034216070803.dkr.ecr.us-east-1.amazonaws.com/wtp:latest
COPY target/test-1.0-SNAPSHOT.jar app.jar
EXPOSE 9999
ENV JAVA_OPTS = "-XX:MaxRAMPercentage=70.0 -XX:InitialRAMPercentage=70.0"
ENTRYPOINT java ${JAVA_OPTS} -jar app.jar --spring.profiles.active=dev