from openjdk:8u275

copy . /dna-simians
workdir /dna-simians

run apt-get update || true
run apt-get install -y maven
run mvn install

expose 8080
cmd ["java", "-jar", "target/dna-simians-0.0.1.jar"]
