import org.testcontainers.DockerClientFactory;
import org.junit.jupiter.api.Test;

public class DockerConnectionTest {

    @Test
    void testDockerEnvironment() {
        String dockerInfo = DockerClientFactory.instance().client().infoCmd().exec().toString();
        System.out.println("Docker Info: " + dockerInfo);
    }
}
