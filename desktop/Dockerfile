FROM eclipse-temurin:25-jre
WORKDIR /app

COPY build/libs/desktop.jar ./callerble-desktop.jar

ENTRYPOINT ["java", "--enable-preview", "-jar", "callerble-desktop.jar"] 