import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;

/**
 * Working with MockServer example.
 *
 * REST API:
 *   PUT http://localhost:MOCK_SERVER_PORT/status       Check status of mock server
 *   PUT http://localhost:MOCK_SERVER_PORT/stop         Stop server
 *   POST http://localhost:MOCK_SERVER_PORT/validate    Check work of example
 */
public class MockServerExample {

    private static final int SLEEP_TIME = 60000;        // One minute
    private static final int MOCK_SERVER_PORT = 7777;
    private static ClientAndServer mockServer;

    public static void main(String[] args) {
        try {
            setMockServerExpectation(mockServer);
            // Start mock server on localhost and port
            mockServer = startClientAndServer(MOCK_SERVER_PORT);
            while (mockServer.isRunning()) {
                Thread.sleep(SLEEP_TIME);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (mockServer != null && mockServer.isRunning()) mockServer.stop();
        }
    }

    /**
     * Set expectation for server behaviour.
     * @implNote you could set several expectations.
     * @param clientAndServer mock server
     */
    private static void setMockServerExpectation(ClientAndServer clientAndServer) {
        // Set up behavior for endpoint /validate
        clientAndServer.when(
            // If server receive POST request to endpoint /validate
            HttpRequest.request().withMethod("POST").withPath("/validate")
        ).respond(
            // Then send response with 200 resp code and some headers and some body
            HttpResponse.response()
                .withStatusCode(200)
                .withHeaders(
                    new Header("Content-Type", "application/json; charset=utf-8"),
                    new Header("Set-Cookie", "SESSION-TOKE=hello_cookie; Path=/"))
                .withBody("{\"status\":123}")
        );
    }
}
