package tests.functional.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import tests.functional.api.dtos.PostSectorRequest;
import tests.functional.api.dtos.PostSectorResponse;
import tests.functional.api.dtos.PutSectorRequest;
import tests.functional.api.dtos.PutSectorResponse;
import tests.functional.helpers.BaseClient;

import java.io.UnsupportedEncodingException;

public class CompaniesServiceClient extends BaseClient {

    public PostSectorResponse PostSector(PostSectorRequest sectorToCreate) {
        HttpPost request = new HttpPost("http://localhost:8080/api/v1/sectors");
        request.addHeader("Content-Type", "application/json");
        try {
            request.setEntity(new StringEntity(objectMapper.writeValueAsString(sectorToCreate)));
            return this.execute(request, PostSectorResponse.class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PutSectorResponse PutSector(PutSectorRequest sectorToUpdate) {
        HttpPut request = new HttpPut("http://localhost:8080/api/v1/sectors/" + sectorToUpdate.getId());
        request.addHeader("Content-Type", "application/json");
        try {
            request.setEntity(new StringEntity(objectMapper.writeValueAsString(sectorToUpdate)));
            return this.execute(request, PutSectorResponse.class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
