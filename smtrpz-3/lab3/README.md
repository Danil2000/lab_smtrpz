### You need:

docker<br>
docker compose

## Run project:

cd lab3/config-server/configdir
git init
git add -A
git commit -m "..."

cd lab3
docker-compose build
docker-compose up --scale eureka-client=2

Eureka Server URL: http://localhost:8761<br>
API-Gateway URL: http://localhost:8080<br>
Service 1 URL: http://localhost:8081<br>
Service 2 URL: http://localhost:8082<br>
Config Server URL: http://localhost:8888<br>
