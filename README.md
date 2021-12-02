# transactions

## Objective

Simulate a financial environment in terms of processing transactions on accounts.

## Running the project

### Prerequisites

* Have Java 11+ installed on your machine (I installed Java 17). I found that my `$JAVA_HOME` environment variable was incorrect, pointing to a JRE, not a JDK. Solutioned it with [this StackOverflow post](https://stackoverflow.com/questions/43496192/java-home-should-point-to-a-jdk-not-a-jre) and [this other post](https://vitux.com/how-to-setup-java_home-path-in-ubuntu/).

* Install Maven. I followed [this tutorial](https://linuxize.com/post/how-to-install-apache-maven-on-ubuntu-20-04/), which were using apt. But this uses an outdated version of Maven, so I used [this tutorial](https://linuxize.com/post/how-to-install-apache-maven-on-ubuntu-18-04/) to configure it. Instead of creating a .sh script inside the `profile.d` dir, I put the `export`s in my `.zshrc` file, which is more convenient.

* It is not mandatory, but using a IDE helps developing the project. I am using VisualStudio Code, and created the project using the `Quarkus Tools for Visual Studio Code` extension to create it.


### Database

The database used is PostgreSQL, via Docker, as per [this tutorial](https://renatogroffe.medium.com/postgresql-pgadmin-4-docker-compose-montando-rapidamente-um-ambiente-para-uso-55a2ab230b89). It is setup via Docker, using the script `start-database.sh`

When starting for the first time, you need to enter the container shell:

```bash
docker exec -it dev-postgres bash
psql -U postgres
```

and manually create the database:

```SQL
CREATE DATABASE quarkus;
```

Every time the application is restarted the database columns are dropped and created again. To avoid this, in the `.env` file, change the `QUARKUS_HIBERNATE_ORM_DATABASE_GENERATION` parameter from `drop-and-create` to `update`. More details in the documentation [[1](https://quarkus.io/guides/datasource#jdbc-datasource)], [[2](https://access.redhat.com/documentation/en-us/red_hat_build_of_quarkus/1.7/html-single/configuring_data_sources_in_your_quarkus_applications/index)]

### Database on dev mode

Info from [here](https://hub.docker.com/_/postgres)
You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

The application restarts automagically when files are changed. As explained in the [design decisions](#design-decisions) section, if you keep the default configurations this will reset the data on each restart. Refer to the aforementioned section to know how to change this behavior.

The project uses environment variables. On dev mode, the [`.env` file method](https://quarkus.io/guides/config-reference#env-file) is used, and on deploying externally it should rely on [environment variables](https://12factor.net/config). A file named `sample.env` is available for testing; to use it, simply rename it to `.env` and Quarkus will load it automatically. It is not recommended to leave production values on it, so be careful.

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

To run the application as a Docker container, use the script `start-quarkus-container.sh`.

## Tests

To run the tests from the command line, in the root directory use the command `mvn verify`. You can also, on your IDE, run the tests individually (tested in VSCode). In order to test, comment all the `QUARKUS_DATABASE_*` and `QUARKUS_HIBERNATE_*` environment variables in the `.env` file, leaving the `QUARKUS_DATASOURCE_DEVSERVICES_ENABLED` as `true`. This will create a testing database, that can be used in the tests. Coverage reports are available, after the test, in `target/jacoco-report`, as per the [documentation](https://quarkus.io/guides/tests-with-coverage).

## Contributing

This project follows `git-flow`: on adding a new feature, fixing a bug or similar tasks, create a branch named `<feature/bugfix/hotfix/...>/<item-name>` from the `main` branch. After testing, merge it to the `development` branch and, after validation, merge it to the `main` branch.

## Documentation

A SwaggerUI endpoint can be used when running the application in dev mode at http://localhost:8080/q/swagger-ui/. It was build following the [standard framework way](https://quarkus.io/guides/openapi-swaggerui), and currently only shows the endpoints, without much detailed information. Use it only as a means to quickly check the endpoints.

A Postman collection is available in the `Transaction.postman_collection.json` file. To use it in Postman, you will need to create and populate an environment. Sample values for this environment, with development configurations, follow:

```bash
host=localhost:8080
api_version=v1
account_id=1
```


## Framework

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Design decisions

* ORM entities as defined in the [Repository pattern](https://quarkus.io/guides/hibernate-orm-panache#solution-2-using-the-repository-pattern)

* I decided to keep the OperationTypes in an `enum` instead of in the database. The idea behind this is that OperationTypes should change rarely, and in the event of a change the code to deal with the new type logic would need to change as well.
    * A counterargument for this would be that adding a new operation type solely on the database could be handled by a different microsservice. While this is certainly possible, such an implementation is still vague, and as this is a MVP of sorts the simplicity of keeping in an `enum` is still justified, in my opinion.

* The database need to be run locally only for development purposes; in a productive environment, it should be in a different container/VM/cloud service/pod/anything. For the dev environment, then, it saves its data to a temporary directory defined in the `start-database.sh` file. Initially I kept this directory inside the repository, but as the Postgres container takes ownership of such dir, and this causes problems when building the Docker image, I preferred to keep it elsewhere. If you want to put the database directory in a more convenient place for you, change the `DATABASE_DATA_DIR` variable in the `start-database.sh` script.

* The `document_number` value is not defined as unique in the database/ORM, so you can have multiple users with the same document number. This was mainly left as a means to simplify the development, but can be interpreted as a same person in possession of multiple accounts.

## Technical debts

* The docker-compose is broken, prefer to build and run each container separately

* Database ids are exposed as account and transaction ids. This is not a good practice: database ids should be hidden from the user, and instead a generated uuid should be exposed. For simplicity I kept the id for now.

* Docker build is consuming a ton more memory and taking a lot more after I stopped using `application.properties`.

* The Balance entity should have a field for showing when the balance was calculated, and probably something more robust to avoid fraud on the calculation/sending
