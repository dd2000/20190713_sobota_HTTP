package _1_HTTP;

import org.apache.http.Header;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.Closeable;
import java.io.IOException;

import static org.junit.Assert.*;

public class _1_appTest {

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

    }

} // class _1_appTest