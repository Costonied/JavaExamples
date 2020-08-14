import org.mockserver.integration.ClientAndServer;

/**
 * Main class for MANIFEST file which will be used by jar.
 * To build single jar artifact: mvn install
 * and looking final jar in ./target dir
 * To start: java -jar jar-name.jar
 *
 * @implNote In the example we using org.mockserver.integration.ClientAndServer
 *           just to show that dependency will be in our single JAR artifact
 */
public class MainClassOfSingleJar {
    private static ClientAndServer mockServer;
    public static void main(String[] args) {
        mockServer = ClientAndServer.startClientAndServer(9999);
        mockServer.stop();
        System.out.println("Is mock server running: " + mockServer.isRunning());
    }
}
