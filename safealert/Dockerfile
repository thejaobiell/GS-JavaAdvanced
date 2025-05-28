# Usa uma imagem base com JDK 17
FROM eclipse-temurin:17-jdk

# Cria o diretório da aplicação
WORKDIR /app

# Copia o JAR gerado
COPY target/safealert-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta usada pelo Spring Boot
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]
