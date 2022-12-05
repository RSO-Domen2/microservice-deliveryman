# Domen2: Deliveryman metadata microservice

## Prerequisites

PostgreSQL image
```bash
docker run -d --name pg-deliveryman -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=deliveryman-metadata -p 5432:5432 postgres:13
```

PostgreSQL with network
```bash
docker run -d --name pg-deliveryman -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=deliveryman-metadata -p 5432:5432 --network rso-deliveryman postgres:13
docker inspect pg-deliveryman
```

Run image on the same network
```bash
docker run --name deliveryman -p 8080:8080 --network rso-deliveryman -e KUMULUZEE_DATASOURCES0_CONNECTIONURL=jdbc:postgresql://pg-deliveryman:5432/deliveryman-metadata dmohorcic/microservice-deliveryman:1.0.0-SNAPSHOT
```

## Build and run commands
```bash
mvn clean package
cd api/target
java -jar deliveryman-api-1.0.0-SNAPSHOT.jar
```
Available at: localhost:8080/v1/deliveryman

## Kubernetes

Deploy to Kubernetes
```bash
kubectl create -f k8s/deliveryman-deployment.yaml
kubectl apply -f k8s/deliveryman-deployment.yaml
```

Update secrets (requires k8s/secrets.yaml file)
```bash
kubectl apply -f k8s/secrets.yaml
```

Zunanji api:
- vreme (cena dostavljalcev na kolesih/peš se poveča)
- routing (za razdalje)
- naslovi v koordinate