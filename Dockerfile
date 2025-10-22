# 1) Build stage
FROM gradle:8.10.2-jdk21-alpine AS build
WORKDIR /home/gradle/src
COPY . .
# 캐시 효율: 종속성 먼저 받기 (옵션)
# RUN gradle dependencies
RUN gradle clean bootJar -x test

# 2) Runtime stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","/app/app.jar"]