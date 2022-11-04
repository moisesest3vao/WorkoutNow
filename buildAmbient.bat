docker network create workoutnow --driver bridge
docker pull postgres
docker run --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -p 5432:5432 -d postgres
docker run --name keycloak -p 8081:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:18.0.0 start-dev
docker run -d --network workoutnow -p 2181:2181 --name zookeeper-server -e ALLOW_ANONYMOUS_LOGIN=yes bitnami/zookeeper:latest
docker run -d --network workoutnow -p 9092:9092 --name kafka-server -e ALLOW_PLAINTEXT_LISTENER=yes -e KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper-server:2181 -e KAFKA_CFG_ADVERTISED_LISTENERS=PLAINTEXT://127.0.0.1:9092 -e KAFKA_BROKER_ID=1 -e KAFKA_CFG_LISTENERS=PLAINTEXT://:9092 bitnami/kafka:latest