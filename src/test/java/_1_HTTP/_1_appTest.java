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

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

import static org.junit.Assert.*;

public class _1_appTest {

// --------------------
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

// -------------------------
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

// -----------------------
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

//---------------------
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


// -------------------------------------
    // Test 5 - testJavaAPI
    @Test
    public void testJavaApi() throws IOException {
        URL url = new URL("https://sdacademy.pl");
        URLConnection urlConnection = url.openConnection();
        urlConnection.addRequestProperty("Accept-Encoding", "gzip");
        urlConnection.addRequestProperty("User-Agent", "Chrome");
        InputStream inputStream = urlConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(inputStream)));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
    } // Test 5 - testJavaAPI


// -------------------------------------
    // Test 6 - zamiast kodu strony pobranie danych w formacie JSON --> skopiuj i popraw Test1
    @Test
    public void getPolandInfo(){

    } // test 6 - getPolandInfo()


// -------------------------------------
// ============================
// do zależności (Dependences) w pliku pom.xml trzeba dodać:  (<- sprawdź plik pom.xml, co jest dodane)
//    <dependency>
//           <groupId>com.google.code.gson</groupId>
//           <artifactId>gson</artifactId>
//           <version>2.8.2</version>
//    </dependency>
// ============================

    // Test 7 - zamiana  JSON na GSON i odwrotnie GSON na JSON
    @Test
    public void testGsonParse(){

    } // test 7 - testGsonParse()


// -------------------------------------
    // Test 8 - zamiana  JSON na GSON w celu pobrania (wyłuskania) danych z JSON
    //sentencja do zbudowania:  Polska ma ludności: x, graniczy z:... a po włosku to:...
    @Test
    public void testBuildSentence(){

    } // test 8 - testBuildSentence()


// -------------------------------------
    // Test 9
    @Test
    public void testBuildJsonFromObject(){

    } // test 9 - testBuildJsonFromObject()


// -------------------------------------
    // Test 10
    @Test
    public void testBuildObjectFromJson(){

    } // test 10 - testBuildObjectFromJson()


// -------------------------------------
// Test 11  - potrzebuje klasy Student
@Test
public void sendStudentData() throws IOException{

} // test 11 - sendStudentData()


// -------------------------------------


} // class _1_appTest