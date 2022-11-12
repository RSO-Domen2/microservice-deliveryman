# Domen2: Deliveryman metadata microservice

## Prerequisites

```bash
docker run -d --name pg-deliveryman -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=deliveryman-metadata -p 5432:5432 postgres:13
```

## Build and run commands
```bash
mvn clean package
cd api/target
java -jar deliveryman-api-1.0.0-SNAPSHOT.jar
```
Available at: localhost:8080/v1/deliveryman

## Kubernetes
```bash
kubectl create -f deliveryman-deployment.yaml
kubectl apply -f deliveryman-deployment.yaml
```