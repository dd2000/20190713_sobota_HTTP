package _1_HTTP;

import org.apache.http.Header;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.EntityTemplate;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.Closeable;
import java.io.IOException;

import static org.junit.Assert.*;

public class _1_appTest {

    // test 1
    @Test
    public void testGetSdAcademy() throws IOException{

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet httpGet = new HttpGet("http://sdacademy.pl");

        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        StatusLine statusLine = httpResponse.getStatusLine();
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        String reasonPhrase = statusLine.getReasonPhrase();

        System.out.println("Status: "+statusCode+", description: "+reasonPhrase);

        Header[] allHeaders = httpResponse.getAllHeaders();
        for (Header h: allHeaders
             ) {
            System.out.println("Header name: "+ h.getName()+", value:"+h.getValue());
        }
        if (statusCode<200 || statusCode>=300){
            System.out.println("Coś poszło nie tak !");
            return;
        }
        BasicResponseHandler basicResponseHandler = new BasicResponseHandler();
        String responseAsString = basicResponseHandler.handleResponse(httpResponse);
        System.out.println(responseAsString);

    } // test 1 - testGetSdAcademy() throws IOException

    // test 2
    @Test
    public void testGetSdAcademyByIp() throws IOException {

        try (CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build()) {
            HttpGet httpGet = new HttpGet("http://104.24.122.121");
            httpGet.addHeader("HOST","sdacademy.pl");
            try (CloseableHttpResponse httpResponse = closeableHttpClient.execute(httpGet)) {
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                System.out.println(statusCode);
                if (statusCode < 200 || statusCode >= 300) {
                    System.out.println("Cos poszło nie tak");
                    return;
                }
                BasicResponseHandler basicResponseHandler = new BasicResponseHandler();
                String responseAsString = basicResponseHandler.handleResponse(httpResponse);
                System.out.println(responseAsString);
            }
        }
    } // test 2 - testGetSdAcademyByIp()

    // test 3 => zwraca kod: status 200, a powinien zareagować na próbę usunięcia, gdy URI: http://sdacademy.pl
    @Test
    public void testDeleteSdAcademy() throws IOException{

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpDelete httpDelete = new HttpDelete("http://google.pl"); // teraz OK, status 405

        CloseableHttpResponse httpResponse = httpClient.execute(httpDelete);

        StatusLine statusLine = httpResponse.getStatusLine();
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        String reasonPhrase = statusLine.getReasonPhrase();

        System.out.println("Status: "+statusCode+", description: "+reasonPhrase);

        Header[] allHeaders = httpResponse.getAllHeaders();
        for (Header h: allHeaders
        ) {
            System.out.println("Header name: "+ h.getName()+", value:"+h.getValue());
        }
        if (statusCode<200 || statusCode>=300){
            System.out.println("Coś poszło nie tak !");
            return;
        }
        BasicResponseHandler basicResponseHandler = new BasicResponseHandler();
        String responseAsString = basicResponseHandler.handleResponse(httpResponse);
        System.out.println(responseAsString);

    } // test 3 - testDeleteSdAcademy()

    // test 4 => wysyłanie danych (POST) na URI: http://sdacademy.pl
    @Test
    public void testSendDataToSdAcademy() throws IOException{

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost("http://sdacademy.pl"); //  OK - status 200; status 405 - gdy URI: http://google.pl
        //HttpPost httpPost = new HttpPost("http://google.pl"); //  OK - status 200; status 405 - gdy URI: http://google.pl

        httpPost.setEntity(EntityBuilder.create()
                            .setText("Jak się macie ?")
                            .build());

        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

        StatusLine statusLine = httpResponse.getStatusLine();
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        String reasonPhrase = statusLine.getReasonPhrase();

        System.out.println("Status: "+statusCode+", description: "+reasonPhrase);

        Header[] allHeaders = httpResponse.getAllHeaders();
        for (Header h: allHeaders
        ) {
            System.out.println("Header name: "+ h.getName()+", value:"+h.getValue());
        }
        if (statusCode<200 || statusCode>=300){
            System.out.println("Coś poszło nie tak !");
            return;
        }
        BasicResponseHandler basicResponseHandler = new BasicResponseHandler();
        String responseAsString = basicResponseHandler.handleResponse(httpResponse);
        System.out.println(responseAsString);

    } // test 4 - testSendDataToSdAcademy()




} // class _1_appTest