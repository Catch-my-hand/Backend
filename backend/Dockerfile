FROM openjdk:11-jdk

VOLUME /tmp

# RUN ./gradlew bootJar

# CMD [ "./", "gradlew", "bootJar" ]

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
