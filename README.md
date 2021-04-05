<img src = "https://i.ibb.co/hCfWxPp/guimsmendes-dna-simians-dark.png">

[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=guimsmendes_dna-simians)](https://sonarcloud.io/dashboard?id=guimsmendes_dna-simians)

[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=guimsmendes_dna-simians&metric=coverage)](https://sonarcloud.io/dashboard?id=guimsmendes_dna-simians)[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=guimsmendes_dna-simians&metric=ncloc)](https://sonarcloud.io/dashboard?id=guimsmendes_dna-simians)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=guimsmendes_dna-simians&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=guimsmendes_dna-simians)[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=guimsmendes_dna-simians&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=guimsmendes_dna-simians)

[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=guimsmendes_dna-simians&metric=sqale_index)](https://sonarcloud.io/dashboard?id=guimsmendes_dna-simians)[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=guimsmendes_dna-simians&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=guimsmendes_dna-simians)[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=guimsmendes_dna-simians&metric=bugs)](https://sonarcloud.io/dashboard?id=guimsmendes_dna-simians)

## Tópicos
* [Descrição](#descrição)
* [Desenho da Solução](#desenho-da-solução)
* [Tecnologias Utilizadas](#tecnologias-utilizadas)
* [Como rodar a aplicação](#como-rodar-a-aplicação)
* [Observability](#observability)
* [Desenvolvedores](#desenvolvedores)
* [Links Úteis](#links-úteis)


## Descrição

Este projeto tem o objetivo de implantar uma aplicação **Dna Simians** utilizando como tecnologia principal Spring Boot e Clean Architecture para disponibilizar um recurso para validação de um determinado Dna recebido. 
O Dna recebido será validado de acordo com os caracteres que representam as bases nitrogenadas de um Dna, além de definir o tipo de Dna ao qual se enquadra,**humano** ou **símio.** 


* A aplicação poderá ser utilizada com chamadas via **API**, via domínio implantado pela **AWS Elastic Compute Cloud** (**URL:** http://dna-simians-elb-390167918.us-east-2.elb.amazonaws.com/). Confira o passo a passo para se conectar com a API no tópico [Como rodar a aplicação: API](#api).


### Recursos

A API irá expôr 2 endpoints a fim de realizar a **verificação**, **postagem** no banco de dados de um determinado tipo de DNA e **recuperar** o número de casos registrados para cada tipo de DNA.


- **POST /simian** - recebe como RequestBody um array de String com as informações de registro de um determinado DNA (limitado aos caracteres referenciados para as bases nitrogenadas: **A, C, T e G.**
Realiza a análise do Dna recebido para definir se é um Dna **Humano** ou **Símio** conforme modelo abaixo. Se possuir uma sequência **horizontal**, **vertical** ou **diagonal** de **4** ou mais bases nitrogenadas **iguais**, trata-se do Dna de um Símio: 

<img src = "https://i.ibb.co/5TxKgjb/Screenshot-from-2021-04-04-19-17-20.png">

Caso o DNA seja identificado como um símio, é retornado um **HTTP-200-OK** , caso
contrário um **HTTP-403-FORBIDDEN** .
Após a verificação, o registro é incluído no banco de dados da aplicação.



- **GET /stats** - realiza uma busca no banco de dados registrado na aplicação retornando o **número** de Dna's registrados para **humanos**, **símios**, e a **relação** entre os dois conforme Response abaixo:
> **{"count_mutant_dna": 40, "count_human_dna": 100: "ratio": 0.4}**

Além dos recursos informados, a rota também contém um endpoint do **Swagger** que expõe a documentação da API:
> http://dna-simians-elb-390167918.us-east-2.elb.amazonaws.com/swagger-ui/index.html


## Desenho da Solução
<img src = "dnaSimiansDiagram.png">
 

## Tecnologias utilizadas

#### Desenvolvimento: [<img src="https://img.shields.io/static/v1?label=spring&message=2.4.4&color=brightgreen&style=for-the-badge&logo=SPRING" width = 120>](https://spring.io/) [<img src="https://img.shields.io/static/v1?label=jdk&message=1.8.0_271&color=orange&style=for-the-badge&logo=JAVA" width = 125>](https://www.oracle.com/br/java/technologies/javase/javase-jdk8-downloads.html) [<img src="https://img.shields.io/static/v1?label=awspring cloud&message=2.3.0&color=orange&style=for-the-badge&logo=SPRING" width = 170>](https://docs.awspring.io/spring-cloud-aws/docs/current/reference/html/index.html#messaging) [<img src="https://img.shields.io/static/v1?label=lombok&message=1.18.18&color=red&style=for-the-badge&logo=" width = 115>](https://projectlombok.org/)

#### Build/Packaging : [<img src="https://img.shields.io/static/v1?label=apache&message=maven&color=red&style=for-the-badge&logo=" width = 120>](https://maven.apache.org/)

#### Tests: [<img src="https://img.shields.io/static/v1?label=junit&message=5&color=green&style=for-the-badge&logo=" width = 70>](https://junit.org/junit5/) [<img src="https://img.shields.io/static/v1?label=mockito&message=1.10.19&color=green&style=for-the-badge&logo=" width = 125>](https://site.mockito.org/) [<img src="https://img.shields.io/static/v1?label=rest-assured&message=3.0.0&color=green&style=for-the-badge&logo=" width = 142>](https://rest-assured.io/) [<img src="https://img.shields.io/static/v1?label=cucumber&message=6.8.1&color=green&style=for-the-badge&logo=cucumber" width = 130>](https://cucumber.io/)


#### Inspection: [<img src="https://img.shields.io/static/v1?label=sonarcloud&message=3.7.0&color=orange&style=for-the-badge&logo=sonarcloud" width = 150>](https://sonarcloud.io/dashboard?id=guimsmendes_dna-simians)


#### API Documentation: [<img src="https://img.shields.io/static/v1?label=swagger&message=3.0.0&color=brightgreen&style=for-the-badge&logo=swagger" width = 125>](http://dna-simians-elb-390167918.us-east-2.elb.amazonaws.com/swagger-ui/index.html)

#### Deploy: [<img src="https://img.shields.io/static/v1?label=aws&message=EC2&color=orange&style=for-the-badge&logo=" width = 80>](https://aws.amazon.com/pt/ec2/) [<img src="https://img.shields.io/static/v1?label=docker&message=action&color=blue&style=for-the-badge&logo=docker" width = 120>](https://aws.amazon.com/pt/elasticbeanstalk/)

#### Observability: [<img src="https://img.shields.io/static/v1?label=spring&message=actuator&color=brightgreen&style=for-the-badge&logo=SPRING" width = 130>](http://dna-simians-elb-390167918.us-east-2.elb.amazonaws.com/actuator) [<img src="https://img.shields.io/static/v1?label=aws&message=cloudwatch&color=brightgreen&style=for-the-badge&logo=" width = 130>](https://aws.amazon.com/pt/cloudwatch/)


## Como rodar a aplicação

### API

**Via AWS EC2:**
* Para rodar a API, foi disponibilizado um host via deploy na AWS EC2 - região us-east-2 para acessar facilmente os endpoints via Postman:
	* > http://dna-simians-elb-390167918.us-east-2.elb.amazonaws.com/


**Collection:**
* Foi disponibilizada uma collection via Postman com exemplos de chamadas para casos de sucesso, tanto para Dna's humanos ou símios, e casos de erro:
	* > https://www.getpostman.com/collections/972a90c052f90f84239f


**Endpoints criados:**
* `/actuator` - Retorna os endpoints com informações de saúde da aplicação
* `/swagger-ui/index.html` - Retorna a documentação da API
* POST `/simian` - Realiza a validação do tipo do Dna e o registro das informações inseridas no body seguindo o modelo JSON e respeitando o tamanho da String para o número de recursos, formando uma tabela NxN:
	* {"dna": "ACTG", "CTGA", "AAAA", "TTTT"}
* GET `/stats` - Realiza a busca dos números de Dna registrados para humanos, símios e a relação entre os dois.
		

## Database

As informações inseridas via API ao realizar um **POST** no endpoint **"/simian"** e buscadas pelo **GET** no endpoint **"/stats"** estão armazenadas em uma instância **RDS** da AWS para um banco de dados **PostgreSQL**: dna-database-1.


Abaixo segue o modelo de dados deste banco de dados:

### Database dna-database-1 -> Schema public -> Table dna
|dnaId|dnaSequence|dnaType|transactionDate|
| -------- | -------- | -------- |-------- |
|**(VARCHAR 36) - PrimaryKey** "36 digit dna UUID" |**(VARCHAR 255)** "[A, T, C, G]"|**(VARCHAR 20)** HUMAN or SIMIAN|**(DATE)** yyyy-MM-dd|

## Observability



## Desenvolvedores

[<img src="https://i.ibb.co/swYk9yk/IMG-20200826-164306.jpg" width=115 > <br> <sub> Guilherme Mendes </sub>](https://github.com/guimsmendes) |
| :---: |  


## Links úteis
* [Deploy de um container Docker em uma instância EC2](https://www.javacodegeeks.com/2019/10/deploy-spring-boot-application-aws-ec2-instance.html)
* [Spring Cloud - Messaging](https://docs.awspring.io/spring-cloud-aws/docs/current/reference/html/index.html#messaging)
* [SQS - Sending and Receiving messages with AWS](https://www.baeldung.com/spring-cloud-aws-messaging)

