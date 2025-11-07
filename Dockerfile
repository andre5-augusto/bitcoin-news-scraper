FROM eclipse-temurin:17-jdk
WORKDIR /app

COPY pom.xml .
RUN apt-get update && apt-get install -y maven && mvn dependency:resolve

COPY . .

RUN mvn clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/bitcoin-news-scraper-1.0-SNAPSHOT.jar"]