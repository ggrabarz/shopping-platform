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