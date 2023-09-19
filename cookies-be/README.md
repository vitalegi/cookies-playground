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

### Setup

#### Configure hosts file

Update `C:\Windows\System32\drivers\etc\hosts` with

```
127.0.0.1	domain1.internal
```

#### Setup TLS - self-signed

<https://www.baeldung.com/spring-boot-https-self-signed-certificate>

```bash
keytool -genkeypair -alias cookies -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore cookies.p12 -validity 3650
```

Disable SSL validation for `api.domain1.internal`.

## Usage

<https://api.domain1.internal:8080/cookies/test1>
