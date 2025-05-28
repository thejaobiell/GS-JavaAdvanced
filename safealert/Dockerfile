# Use a imagem oficial do Maven com JDK 17 para build
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

# Copia os arquivos do projeto (ajuste se usar monorepo ou subpasta)
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
COPY src src

# Dá permissão para o script mvnw
RUN chmod +x mvnw

# Builda o JAR pulando testes
RUN ./mvnw clean package -DskipTests

# --- Segunda etapa: imagem final minimalista para rodar o JAR ---
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copia o JAR buildado da etapa anterior
COPY --from=build /app/target/safealert-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta padrão do Spring Boot (8080)
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
