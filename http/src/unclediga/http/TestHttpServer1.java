package unclediga.http;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.net.InetSocketAddress;

import java.io.IOException;
import java.io.OutputStream;


public class TestHttpServer1 {
  public static void main(String[] args) {
    new TestHttpServer1();
  }

  public TestHttpServer1(){
    try{
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost",8282), 0);

        server.createContext("/app", new MyHandler(server));

        
        System.out.println("Server starting...");
        server.start();
        

      }catch(IOException e){
        e.printStackTrace();
      }
  }

  protected class MyHandler implements HttpHandler{

    private HttpServer server;
    public MyHandler(HttpServer server){
      this.server = server;
    }

    @Override
    public void handle(HttpExchange t) throws IOException {
      String method = t.getRequestMethod();
      // curl -X GET localhost:8282/app
      String resp = "<html><body><h3>Hello!</h3></body></html>";
      if(method.equals("DELETE")){
        // curl -X DELETE localhost:8282/app
        resp = "<html><body><h3>server stopped...</h3></body></html>";
      }
      t.sendResponseHeaders(200, resp.getBytes().length);
      OutputStream os = t.getResponseBody();
      os.write(resp.getBytes());
      os.close();
      if(method.equals("DELETE")){
       System.out.println("Server stopped...");
       server.stop(0);
      }
    }  
  }
}