package ru.unclediga.examples.http;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.net.InetSocketAddress;

import java.io.IOException;
import java.io.OutputStream;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;


public class TestRestClient {

  public static void main(String[] args) {
    System.out.println("Client run...");
    new TestRestClient().run();
  }

  public TestRestClient(){
    //Client client = ClientBuilder.newClient();
  }

  public void run(){
    System.out.println("GET...");
    Response response = ClientBuilder.newClient().target("http://localhost:8282/app")
  .request(MediaType.TEXT_PLAIN).get();

  }
}