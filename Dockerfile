from openjdk:8u275

copy . /dna-simians
workdir /dna-simians

run apt-get update || true
run apt-get install -y maven
run mvn -DAWS_ACCES_KEY=AKIA26IVFNCYGJSNLZ5M install

expose 8080
cmd ["java", "-jar", "target/dna-simians-1.0.0.jar"]
