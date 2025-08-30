# Use uma imagem oficial do Java
FROM openjdk:17-jdk-alpine

# Crie um diretório para a aplicação
WORKDIR /app

# Copie o jar gerado pelo build
COPY target/*.jar app.jar

# Exponha a porta (Render passa via variável PORT)
ENV PORT 8080
EXPOSE $PORT

# Comando para rodar o jar
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=$PORT"]
