FROM gradle:8.0.1-jdk17-alpine AS compile-image

WORKDIR /app
COPY src /app/src
#COPY gradle.properties /app/
COPY *.kts /app/
USER root
RUN chown -R gradle /app
USER gradle
RUN gradle build -x check -x test

FROM openjdk:17-jdk-alpine AS runtime-image

EXPOSE 8080
COPY --from=compile-image /app/build/libs/adfmp1h23-waste-server-0.0.1-SNAPSHOT.jar /app.jar
CMD [ "/bin/sh", "-c", "java -jar /app.jar"]
