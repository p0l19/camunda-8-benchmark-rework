FROM maven:3-openjdk-17 as builder
WORKDIR /usr/src/app
COPY pom.xml pom.xml
RUN mvn dependency:resolve-plugins dependency:resolve package -Dspring-boot.repackage.skip=true -Dmaven.test.skip=true -DskipTests -DskipChecks
COPY src/ src/
RUN mvn install -Dspring-boot.repackage.layers.enabled=true -Dmaven.test.skip=true -DskipTests -DskipChecks

FROM azul/zulu-openjdk-alpine:17-jre-headless
COPY --from=builder /usr/src/app/target/camunda-8-benchmark-rework-0.0.1-SNAPSHOT.jar ./app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
