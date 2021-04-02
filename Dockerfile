from openjdk:8u275

ARG AWS_ACCESS_KEY
# ARG AWS_SECRET_KEY
ARG RDS_PASSWORD

copy . /dna-simians
workdir /dna-simians

run apt-get update || true
run apt-get install -y maven
run mvn install

expose 8080
cmd ["java", "-jar", "target/dna-simians-1.0.0.jar"]
