package com.example.rest.dto;

import static com.datastax.oss.protocol.internal.util.Bytes.toHexString;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Optional;
import lombok.Data;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.lang.Nullable;

@Data
public class CassandraPage<T> {
  private final int count;
  private final List<T> message;
  private final String pagingState;
  private final boolean hasNext;

  public CassandraPage(final Slice<T> slice) {
    this.count = slice.getNumberOfElements();
    this.message = slice.getContent();
    this.pagingState = getPagingState(slice);
    this.hasNext = slice.hasNext();
  }

  @Nullable
  private static <T> String getPagingState(final Slice<T> slice) {
    if (slice.hasNext()) {
      CassandraPageRequest cassandraPageRequest = (CassandraPageRequest) slice.nextPageable();
      ByteBuffer pagingState = cassandraPageRequest.getPagingState();
      return toHexString(pagingState);
    } else {
      return null;
    }
  }

  public Optional<String> getPagingState() {
    return Optional.ofNullable(pagingState);
  }
}
