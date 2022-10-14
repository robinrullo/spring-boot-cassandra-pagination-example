package com.example.rest.user;

import com.example.rest.dto.CassandraPage;
import com.example.rest.dto.Paginated;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  private final UserPaginationService userPaginationService;

  public UserController(UserPaginationService userPaginationService) {
    this.userPaginationService = userPaginationService;
  }

  @GetMapping()
  public CassandraPage<User> getAllUsers(final @Valid Paginated paginated) {
    return userPaginationService.getPageOfUsers(paginated);
  }
}
