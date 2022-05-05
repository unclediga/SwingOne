package ru.unclediga.examples.http;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.RuntimeDelegate;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;

public class TestHttpServer1IT  {

  
  private static HttpServer server;

  @BeforeClass
  public static void init() throws Exception{
    server = TestHttpServer1.getTestServer();
    //HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(new ApplicationConfig03(), HttpHandler.class);
    System.out.println("Server started");
    server.start();
  }

  @AfterClass
  public static void stop(){
    System.out.println("Server stopped");
    server.stop(0);
  }

  @Test
  public void t2(){
      Response response = ClientBuilder
      .newClient()
      .target("http://localhost:8282/app")
      .request(MediaType.TEXT_PLAIN)
      .get();

      System.out.println("t2 status info " + response.getStatusInfo());
  }    

  @Test
  public void t1(){
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target("http://localhost:8282/app");
    Invocation.Builder builder = target.request(MediaType.TEXT_PLAIN);

    Response response = builder.get();
    System.out.println("t1 status info " + response.getStatusInfo());
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    String entity = response.readEntity(String.class);
    assertEquals("<html><body><h3>Hello!</h3></body></html>", entity);
  }

  // @Test
  // public void t2(){
  //   Client client = ClientBuilder.newClient();
  //   WebTarget target = client.target("http://ya.ru");
  //   Invocation.Builder builder = target.request(MediaType.TEXT_HTML);

  //   Response response = builder.get();
  //   assertTrue(response.getStatusInfo() == Response.Status.OK);
  // }
}