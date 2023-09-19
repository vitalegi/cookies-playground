# README

Playground in order to locally test cookies behaviour in multi-domain use cases.

## Setup local env

### Generate SSL certificates

```
$env:PATH=$env:PATH + 'C:\Program Files\Git\usr\bin'
openssl req -x509 -nodes -days 365 -newkey rsa:2048 -keyout C:/a/projects/vitalegi/cookies-playground/nginx-selfsigned.key -out C:/a/projects/vitalegi/cookies-playground/nginx-selfsigned.crt
```

```
PS C:\a\projects\vitalegi\cookies-playground\cookie-fe1> openssl req -x509 -nodes -days 365 -newkey rsa:2048 -keyout C:/a/projects/vitalegi/cookies-playground/nginx-selfsigned.key -out C:/a/projects/vitalegi/cookies-playground/nginx-selfsigned.crt
>>
Generating a RSA private key
........+++++
......................+++++
writing new private key to 'C:/a/projects/vitalegi/cookies-playground/nginx-selfsigned.key'
-----
You are about to be asked to enter information that will be incorporated
into your certificate request.
What you are about to enter is what is called a Distinguished Name or a DN.
There are quite a few fields but you can leave some blank
For some fields there will be a default value,
If you enter '.', the field will be left blank.
-----
Country Name (2 letter code) [AU]:
State or Province Name (full name) [Some-State]:
Locality Name (eg, city) []:
Organization Name (eg, company) [Internet Widgits Pty Ltd]:
Organizational Unit Name (eg, section) []:
Common Name (e.g. server FQDN or YOUR name) []:fe.domain1.internal
Email Address []:
```

### Configure hosts file

Update `C:\Windows\System32\drivers\etc\hosts` with

```
127.0.0.1	api.domain1.internal
127.0.0.1	fe.domain1.internal
```

### Disable SSL validation

In the browser of your choice, open the 2 domains and allow traffic.

### Nginx setup

<http://nginx.org/en/download.html>

`nginx.conf`'s server section
```
server {
    listen 443 ssl http2;
    ssl_certificate     C:/a/projects/vitalegi/cookies-playground/nginx-selfsigned.crt;
    ssl_certificate_key C:/a/projects/vitalegi/cookies-playground/nginx-selfsigned.key;
    server_name fe.domain1.internal;

    location / {
        root   html;
        index  index.html index.htm;
    }
}

server {
    listen 443 ssl http2;
    server_name api.domain1.internal;
    ssl_certificate     C:/a/projects/vitalegi/cookies-playground/nginx-selfsigned.crt;
    ssl_certificate_key C:/a/projects/vitalegi/cookies-playground/nginx-selfsigned.key;

    location / {
        proxy_pass http://localhost:8080;
    }
}
```
