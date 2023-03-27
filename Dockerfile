FROM openjdk:17-oracle
COPY "./target/verbos-irregulares-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 9000
ENTRYPOINT ["java","-jar","app.jar"]