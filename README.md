# transactions

## Objective

Simulate a financial environment in terms of processing transactions on accounts.

## Running the project

### Database

The database used is PostgreSQL, via Docker, as per [this tutorial](https://renatogroffe.medium.com/postgresql-pgadmin-4-docker-compose-montando-rapidamente-um-ambiente-para-uso-55a2ab230b89). It is setup via Docker compose: `docker-compose up -d`
Explain application.properties:
    https://access.redhat.com/documentation/en-us/red_hat_build_of_quarkus/1.7/html-single/configuring_data_sources_in_your_quarkus_applications/index
    https://quarkus.io/guides/datasource#jdbc-datasource

Entities as defined in https://quarkus.io/guides/hibernate-orm-panache#solution-2-using-the-repository-pattern

#### Dev mode

Start with `./start-database.sh`. Once started, enter with `docker exec -it dev-postgres bash`. Then, to enter in the database itself, use `psql -U postgres`

### Dev mode

Info from [here](https://hub.docker.com/_/postgres)
You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

### Debug

For debugging on VSCode on dev mode, attach the following configuration to your `launch.json` config file:

```javascript
    {
        "type": "java",
        "name": "Remote Debug (Attach Quarkus)",
        "request": "attach",
        "hostName": "localhost",
        "port": 5005
    }
```

[source](https://suedbroecker.net/2021/04/29/configure-the-attach-debug-for-quarkus-in-visual-studio-code/)

### Docker


## Tests

To run the tests from the command line, in the root directory use the command `mvn verify`. You can also, on your IDE, run the tests individually (tested in VSCode).

## Contributing

This project follows `git-flow`: on adding a new feature, fixing a bug or similar tasks, create a branch named `<feature/bugfix/hotfix/...>/<item-name>` from the `main` branch. After testing, merge it to the `development` branch and, after validation, merge it to the `main` branch.


## Framework

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .
