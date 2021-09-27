package ru.costonied.examples.xml;

/**
 * The example showing how to switch on communication dump logging to console.
 * Useful for example if you want to see Request/Response for SOAP service.
 *
 * @implSpec Also you could use this parameters as command line
 * parameter (using -D or as environment variable):
 *  -Dcom.sun.xml.ws.transport.http.client.HttpTransportPipe.dump=true
 *  -Dcom.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump=true
 *  -Dcom.sun.xml.ws.transport.http.HttpAdapter.dump=true
 *  -Dcom.sun.xml.internal.ws.transport.http.HttpAdapter.dump=true
 *  -Dcom.sun.xml.internal.ws.transport.http.HttpAdapter.dumpTreshold=999999
 *
 */
public class SwitchOnCommunicationLogging {
    public static void main(String[] args) {
        System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
        System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dumpTreshold", "999999");
    }
}
