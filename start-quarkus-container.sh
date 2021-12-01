#! /bin/sh
# Build and run Quarkus Docker container according to instructions in https://access.redhat.com/documentation/en-us/red_hat_build_of_quarkus/1.7/html-single/compiling_your_quarkus_applications_to_native_executables/index

IMAGE_NAME=transactions

# Build native executable
./mvnw package -Pnative -Dquarkus.native.container-build=true

# Build the container image
docker build -f src/main/docker/Dockerfile.native -t $IMAGE_NAME .

# Run the container; network "host" needed to access database on localhost
docker run --network="host" -i --rm -p 8080:8080 --env-file .env $IMAGE_NAME
