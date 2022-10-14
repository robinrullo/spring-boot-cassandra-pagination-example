package com.example.rest.user;

import static com.datastax.oss.protocol.internal.util.Bytes.fromHexString;

import com.example.rest.dto.CassandraPage;
import com.example.rest.dto.Paginated;
import lombok.val;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class UserPaginationService {

  final UserRepository userRepository;

  public UserPaginationService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public CassandraPage<User> getPageOfUsers(final Paginated paginated) {
    return getPageOfUsers(paginated.getLimit(), paginated.getPagingState().orElse(null));
  }

  public CassandraPage<User> getPageOfUsers(final Integer limit) {
    return getPageOfUsers(limit, null);
  }

  public CassandraPage<User> getPageOfUsers(final Integer limit,
      final String pagingState) {
    val pageRequest = createCassandraPageRequest(limit, pagingState);
    return getPageOfUsers(pageRequest);
  }

  public CassandraPage<User> getPageOfUsers(
      final CassandraPageRequest cassandraPageRequest) {
    return new CassandraPage<>(userRepository.findAll(cassandraPageRequest));
  }

  private CassandraPageRequest createCassandraPageRequest(final Integer limit,
      @Nullable final String pagingState) {
    Pageable pageRequest = PageRequest.of(0, limit);
    return CassandraPageRequest.of(pageRequest,
        pagingState != null ? fromHexString(pagingState) : null);
  }
}
