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
val testcontainersVersion by extra { "1.17.6" }

dependencies {
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    implementation("org.springframework.boot:spring-boot-starter-actuator")
//    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
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

tasks.register<Exec>("dockerRun") {
    commandLine("docker", "run", "-dit", "-p", "8080:8080", dockerImageName)
    doFirst {
        println("Starting container for image $dockerImageName")
        println("Get your example discount at http://localhost:8080/calculate-price-discount?productId=e737b48b-1fe2-476a-ba2e-85c44f036e86&amount=5")
        println("Stop the container with command:")
        print("docker stop ")
    }
}
