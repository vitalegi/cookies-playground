# README


## Prerequisites

- JDK 17
- Maven

```
$env:M2_HOME = 'C:\a\software\apache-maven-3.9.2'
$env:JAVA_HOME = 'C:\a\software\jdk-20.0.1'
$env:PATH = $env:M2_HOME + '\bin;' + $env:JAVA_HOME + '\bin;' + $env:PATH
```

## Compile

```bash
mvn clean package
```

## Run

```bash
mvn spring-boot:run
```

## Usage

<https://api.domain1.internal:8080/cookies/test1>
