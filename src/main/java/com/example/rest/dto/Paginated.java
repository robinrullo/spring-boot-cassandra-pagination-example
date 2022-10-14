package com.example.rest.dto;

import java.util.Optional;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Data;

@Data
public class Paginated {

  public static final int DEFAULT_LIMIT = 10;

  @Min(1)
  @Max(1000)
  private Integer limit;
  private String pagingState;

  public Optional<String> getPagingState() {
    return Optional.ofNullable(pagingState);
  }

  public Integer getLimit() {
    return limit != null ? limit : DEFAULT_LIMIT;
  }
}
