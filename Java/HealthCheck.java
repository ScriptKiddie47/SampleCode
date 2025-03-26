import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class HealthCheck {
    public static void main(String[] args) throws Exception {

        System.out.println("Arguments: " + args.length);

        // String httpEndpoint = "https://reqres.in/api/users/2";
        String httpEndpoint = "http://localhost:3000/";
        String jsonRequest = """
                {"name":"John", "age":30, "car":null}
                """;
        System.out.println("Request: " + jsonRequest);

        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(httpEndpoint))
                    .header("Content-Type","application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                    .build();

            HttpClient client = HttpClient.newBuilder()
                    .build();

            HttpResponse<String> httpResponse = client.send(httpRequest, BodyHandlers.ofString());

            System.out.println("Status: " + httpResponse.statusCode());
            System.out.println("Response: " + httpResponse.body());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
}

// String filename = "D:\\certs_path\\cacerts"; // cerrtification file path
// System.setProperty("javax.net.ssl.trustStore", fileName);

// Article : https://www.baeldung.com/java-httpclient-post