package tests.functional.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class BaseClient {
    protected final CloseableHttpClient httpClient = HttpClients.createDefault();
    protected final ObjectMapper objectMapper;

    public BaseClient() {
        objectMapper = new ObjectMapper();
    }

    protected <T> T execute(HttpUriRequest request, Class<T> typeToReturn) {
        System.out.println("Executing request");
        System.out.println("URI: " + request.getURI());
        System.out.println("Method: " + request.getMethod());
        System.out.println("Response type: " + request.getMethod());

        try {
            CloseableHttpResponse response = httpClient.execute(request);
            if (response.getEntity() != null) {
                String stringResponse = EntityUtils.toString(response.getEntity());
                System.out.println("Response body: " + stringResponse);
                return objectMapper.readValue(stringResponse, typeToReturn);
            }
            return (T) response;
        } catch (Exception ex) {
            System.out.println("Error when executing the Request. " + ex.getMessage());
            // TODO: Create scenario error handling
        }
        return null;
    }

    protected CloseableHttpResponse execute(HttpUriRequest request) {
        return this.execute(request, CloseableHttpResponse.class);
    }
}
