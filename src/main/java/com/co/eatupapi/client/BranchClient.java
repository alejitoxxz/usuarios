package com.co.eatupapi.client;

import com.co.eatupapi.utils.exception.BusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.UUID;

@Component
public class BranchClient {

    private final RestClient restClient;

    public BranchClient(RestClient.Builder builder, @Value("${branch.service.base-url:http://localhost:8081}") String baseUrl) {
        this.restClient = builder.baseUrl(baseUrl).build();
    }

    public void validateBranchExists(UUID branchId) {
        try {
            restClient.get()
                    .uri("/branches/{id}", branchId)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, (req, res) -> {
                        throw new BusinessException("La sede no existe o no esta disponible: " + branchId);
                    })
                    .toBodilessEntity();
        } catch (RestClientException ex) {
            throw new BusinessException("No fue posible validar la sede: " + branchId);
        }
    }
}
