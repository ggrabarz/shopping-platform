plugins {
    java
    id("org.springframework.boot") version "3.0.1"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "pl.inpost"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_19

repositories {
    mavenCentral()
}

val dockerImageName by extra { "pl.inpost/${project.name}" }
val dockerImageMongo by extra { "pl.inpost/shopping-mongo" }
val testcontainersVersion by extra { "1.17.6" }

dependencies {
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")

    testImplementation("io.rest-assured:rest-assured:5.3.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:mongodb")
}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:${testcontainersVersion}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootBuildImage>("bootBuildImage") {
    imageName.set(dockerImageName)
}

tasks.register<Exec>("dockerBuild") {
    dependsOn(tasks.getByName("bootBuildImage"))
    group = "application"
    workingDir = file("./docker")
    environment("DOCKER_IMAGE_MONGO", dockerImageMongo)
    environment("DOCKER_IMAGE_NAME", dockerImageName)
    commandLine("docker-compose", "build")
}

tasks.register<Exec>("dockerComposeUp") {
    group = "application"
    workingDir = file("./docker")
    environment("DOCKER_IMAGE_MONGO", dockerImageMongo)
    environment("DOCKER_IMAGE_NAME", dockerImageName)
    commandLine("docker-compose", "up", "-d")
    doLast {
        println("Starting service containers...")
        println("Get your example discount at:")
        println("http://localhost:8081/calculate-price-discount?productId=e737b48b-1fe2-476a-ba2e-85c44f036e86&amount=2")
        println("http://localhost:8081/calculate-price-discount?productId=a8ef1604-79a0-4b0a-88af-3a131934398f&amount=15")
        println("http://localhost:8081/calculate-price-discount?productId=09716a47-ab35-4753-8f12-b52e0f9ceca2&amount=21")
    }
}

tasks.register<Exec>("dockerComposeDown") {
    group = "application"
    environment("DOCKER_IMAGE_MONGO", dockerImageMongo)
    environment("DOCKER_IMAGE_NAME", dockerImageName)
    commandLine("docker-compose", "down")
    workingDir = file("./docker")
}

tasks.register<Exec>("dockerRunMongo") {
    group = "application"
    commandLine("docker", "run", "-dit", "-p", "27017:27017", "--name", "shopping-mongo", dockerImageMongo)
    workingDir = file("./docker")
}
