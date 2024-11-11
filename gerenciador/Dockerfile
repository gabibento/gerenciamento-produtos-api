# Utiliza a imagem do OpenJDK como base
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo JAR gerado para dentro do container
COPY target/gerenciador-0.0.1-SNAPSHOT.jar app.jar

# Define o comando padrão para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
