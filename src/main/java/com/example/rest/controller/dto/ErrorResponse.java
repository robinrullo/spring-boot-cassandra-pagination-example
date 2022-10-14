package com.example.rest.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

  private String message;

  public static ErrorResponse create(Throwable throwable) {
    return create(throwable.getMessage());
  }

  public static ErrorResponse create(final String message) {
    return ErrorResponse.builder().message(message).build();
  }
}
