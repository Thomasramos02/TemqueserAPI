# Use uma imagem base com Java 21 LTS
FROM eclipse-temurin:21-jdk-jammy

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo JAR da sua pasta target para o container
# Certifique-se de que o nome do JAR aqui corresponde ao que o Maven gera.
# Pelo seu log, é 'temqueser-0.0.1-SNAPSHOT.jar'
COPY target/temqueser-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta que sua aplicação Spring Boot usa (padrão é 8080)
EXPOSE 8080

# Define o comando para rodar sua aplicação quando o container iniciar
ENTRYPOINT ["java", "-jar", "app.jar"]