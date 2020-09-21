package tests.functional.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import tests.functional.api.dtos.SectorRequest;
import tests.functional.api.dtos.SectorResponse;

import java.io.UnsupportedEncodingException;

public class CompaniesServiceClient {
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private final ObjectMapper objectMapper;

    public CompaniesServiceClient() {
        objectMapper = new ObjectMapper();
    }

    public SectorResponse PostSector(SectorRequest sectorToCreate) {
        HttpPost post = new HttpPost("http://localhost:8080/api/v1/sectors");
        post.addHeader("Content-Type", "application/json");
        try {
            post.setEntity(new StringEntity(objectMapper.writeValueAsString(sectorToCreate)));
            return this.execute(post, SectorResponse.class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private <T> T execute(HttpPost request, Class<T> typeToReturn) {
        System.out.println("Executing request");
        System.out.println("URI: " + request.getURI());
        System.out.println("Method: " + request.getMethod());
        System.out.println("Response type: " + request.getMethod());

        try {
            CloseableHttpResponse response = httpClient.execute(request);
            String stringResponse = EntityUtils.toString(response.getEntity());
            System.out.println("Response body: " + stringResponse);
            return objectMapper.readValue(stringResponse, typeToReturn);
        } catch (Exception ex) {
            System.out.println("Error when executing the Request");
        }
        return null;
    }
}
