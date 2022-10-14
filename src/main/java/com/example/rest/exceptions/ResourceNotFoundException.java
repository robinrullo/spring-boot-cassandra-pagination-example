package com.example.rest.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ResourceNotFoundException extends RuntimeException {

  private final String id;
  private final Class<?> type;

  public ResourceNotFoundException(final String id, final Class<?> type) {
    super(String.format("The following %s with id: %s could not be found.", type.getSimpleName(),
        id));
    this.id = id;
    this.type = type;
  }
}
