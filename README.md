Inpost Shopping Platform
========================
Inpost recruitment assignment - a service calculating discounts for a given amount of products.  
Project structure based on https://reflectoring.io/book/.

Requirements
--------
To run this project you need:

* `java` installed for running the `./gradlew` wrapper
* `docker` and `docker-compose` (contained in [Docker Desktop](https://www.docker.com/products/docker-desktop/))

Prepare mongo image for app and integration tests:
> `./gradlew dockerBuild` - builds both mongo and shopping-platform images

Running
---

### Run in a container

Start/stop both mongo and shopping-platform in containers:
> Start: `./gradlew dockerComposeUp`  
> Stop:  `./gradlew dockerComposeDown`

App is exposed under http://localhost:8081 to avoid port collisions.

### Run on the host machine

You can also run the application outside of docker e.g. for easier debugging,  
but first you need to run the mongo container (unless already started by compose):
> `./gradlew dockerRunMongo`  
> `./gradlew bootRun`

App is exposed under http://localhost:8080 to avoid port collisions.

Test
----
Running unit tests and integration tests:
> `./gradlew test`

Sample requests
---------------
See [calculate-price-discount.http](./http/calculate-price-discount.http)

Next steps:
----------
Simple upgrades that I'd do next:
* split `gradle test` task to `unitTest` and `integrationTest` either using JUnit 5 Tags or a separate source-set for integration (my preferred way) so you can easily run only unit or integration tests
* introduce `springdoc-openapi-ui` to the endpoint to generate OpenAPI and integrate with Swagger UI
* introduce static code analysis tools (lately I've been using detekt and codenarc for Spock with Groovy, prettier for React apps)
* expand the test coverage