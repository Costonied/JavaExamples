package ru.costonied.examples.socket.webserver;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
 * Very simple WEB Server
 *
 * @implNote original article https://habr.com/ru/post/69136/
 */
public class SimpleHttpServer {

  public static void main(String[] args) throws Throwable {
    ServerSocket serverSocket = new ServerSocket(8080);
    while (true) {
      Socket socket = serverSocket.accept();
      System.out.println("Client accepted");
      new Thread(new SocketProcessor(socket)).start();
    }
  }

  private static class SocketProcessor implements Runnable {

    private final Socket socket;
    private final InputStream inputStream;
    private final OutputStream outputStream;

    private SocketProcessor(Socket socket) throws Throwable {
      this.socket = socket;
      this.inputStream = socket.getInputStream();
      this.outputStream = socket.getOutputStream();
    }

    public void run() {
      try {
        String htmlText = "<html><body><h1>Hello from Habrahabr</h1></body></html>";
        readInputHeaders();
        writeResponse(htmlText);
      } catch (Throwable t) {
        /*do nothing*/
      } finally {
        try {
          socket.close();
        } catch (Throwable t) {
          /*do nothing*/
        }
      }
      System.out.println("Client processing finished");
    }

    private void writeResponse(String responseBody) throws Throwable {
      String responseHeaders = "HTTP/1.1 200 OK\r\n" +
          "Server: SimpleHttpServer/001\r\n" +
          "Content-Type: text/html\r\n" +
          "Content-Length: " + responseBody.length() + "\r\n" +
          "Connection: close\r\n\r\n";
      String response = responseHeaders + responseBody;
      outputStream.write(response.getBytes());
      outputStream.flush();
    }

    /**
     * @implNote Метод не несет в себе полезной нагрузки,
     * просто показывает, что из входящего запроса можно читать информацию,
     * например, заголовки запроса и использовать их при необходимости
     */
    private void readInputHeaders() throws Throwable {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
      while(true) {
        String line = bufferedReader.readLine();
        if(line == null || line.trim().length() == 0) {
          break;
        }
      }
    }
  }
}