FROM openjdk:17
COPY "./target/ProyectoFinalDAE-1.jar" "app.jar"
EXPOSE 8112
ENTRYPOINT [ "java", "-jar", "app.jar" ]
