package com.mahasiswa;

import java.net.URI;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // Corrected the URI scheme
        URI baseUri = URI.create("http://127.0.0.1:8080/");
        ResourceConfig config = new ResourceConfig(ControllerMahasiswa.class);

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);

        try {
            server.start();
            System.out.println("Server Berjalan di " + baseUri);
            System.out.println("Tekan Enter untuk menutup....");
            System.in.read();
            server.shutdown();
        } catch (Exception e) {
            // Print the stack trace for better debugging
            e.printStackTrace();
        }
    }
}
