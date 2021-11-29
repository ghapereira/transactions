#! /bin/sh

# Start the test database as a standalone Docker container

DATABASE_DATA_DIR="/tmp/data/postgres-data"
POSTGRES_PASSWORD=atestpasswordnonprod
CONTAINER_POSTGRES_DATA_DIR=/var/lib/postgresql/data
POSTGRES_INNER_PORT=5432
POSTGRES_OUTER_PORT=5432

mkdir -p -- $DATABASE_DATA_DIR

docker run -d --rm \
    --name dev-postgres \
    -e POSTGRES_PASSWORD=$POSTGRES_PASSWORD \
    -v ${DATABASE_DATA_DIR}:$CONTAINER_POSTGRES_DATA_DIR \
    -p $POSTGRES_OUTER_PORT:$POSTGRES_INNER_PORT \
    postgres
