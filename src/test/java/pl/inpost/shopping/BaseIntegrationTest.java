package pl.inpost.shopping;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Testcontainers
public class BaseIntegrationTest {

    @LocalServerPort
    int port;

    @Container
    static final GenericContainer<?> mongoDBContainer = new GenericContainer<>(
            DockerImageName.parse("pl.inpost/shopping-mongo")
                    .asCompatibleSubstituteFor("mongo"))
            .withExposedPorts(27017)
            .waitingFor(Wait.forLogMessage(".*Waiting for connections.*", 1))
            .withEnv("MONGO_INITDB_ROOT_USERNAME", "root")
            .withEnv("MONGO_INITDB_ROOT_PASSWORD", "root")
            .withEnv("MONGO_INITDB_DATABASE", "shopping-platform")
            .withEnv("MONGO_INITDB_USERNAME", "inpost")
            .withEnv("MONGO_INITDB_PASSWORD", "inpost");

    @DynamicPropertySource
    static void registerMongoPort(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.port", mongoDBContainer::getFirstMappedPort);
    }

    protected String url(final String path) {
        return "http://localhost:" + port + path;
    }
}
