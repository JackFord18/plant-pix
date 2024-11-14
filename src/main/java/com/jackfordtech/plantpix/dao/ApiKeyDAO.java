package com.jackfordtech.plantpix.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiKeyDAO {
    @Autowired
    private ApiKeyRepository apiKeyRepository;

    public boolean checkApiKeyExists(String apiKey) {
        return apiKeyRepository.findById(apiKey).isPresent();
    }
}
