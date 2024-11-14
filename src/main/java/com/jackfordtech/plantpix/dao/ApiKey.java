package com.jackfordtech.plantpix.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ApiKey {
  @Id
  private String apiKey;

  public String getApiKey() {
    return apiKey;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }
}