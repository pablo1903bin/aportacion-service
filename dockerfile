# ---------- Etapa de construcción ----------
FROM maven:3.9.9-eclipse-temurin-17-alpine AS builder

# Crea directorio de trabajo y copia el código fuente
WORKDIR /build
COPY pom.xml .
COPY src ./src

# Descarga dependencias y construye el JAR
RUN mvn clean package -DskipTests

# ---------- Etapa de ejecución ----------
FROM eclipse-temurin:17-jdk-alpine

# Variables de entorno opcionales
ENV TZ=America/Mexico_City \
    JAVA_OPTS="-Xms128m -Xmx512m"

# Crea directorio de trabajo
WORKDIR /app

# Copia solo el JAR generado
COPY target/aportacion-service.jar app.jar

# Expone puertos (puedes eliminar 8080 si usas solo 443)
EXPOSE 8086
EXPOSE 8086

# Ejecuta la app
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
