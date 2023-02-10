package com.globits.emr.service;

import com.globits.core.service.GenericService;
import com.globits.emr.dto.UserExtDto;
import com.globits.emr.dto.loginkeycloak.UserKeyCloackDto;
import com.globits.emr.dto.search.UserSearchDto;
import com.globits.security.domain.User;
import com.globits.security.dto.UserDto;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Primary
public interface UserExtService extends GenericService<User, Long> {
    public Page<UserDto> pagingUsers(UserSearchDto dto);

    public UserExtDto deleteById(Long userId);

    public List<UserExtDto> deleteListId(List<Long> userId);
    public ResponseEntity<UserKeyCloackDto> creatUserKeyCloak(UserDto dto);
    public UserDto getCurrentUser();
}
