package com.globits.emr.service.impl;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.emr.EmrConstants;
import com.globits.emr.dto.SystemConfigDto;
import com.globits.emr.dto.UserExtDto;
import com.globits.emr.dto.loginkeycloak.AccessDto;
import com.globits.emr.dto.loginkeycloak.CredentialDto;
import com.globits.emr.dto.loginkeycloak.UserKeyCloackDto;
import com.globits.emr.dto.search.UserSearchDto;
import com.globits.emr.service.SystemConfigService;
import com.globits.emr.service.UserExtService;
import com.globits.emr.utils.RestApiUtils;
import com.globits.security.domain.User;
import com.globits.security.dto.UserDto;
import com.globits.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
@Primary
public class UserExtServiceImpl extends GenericServiceImpl<User, Long> implements UserExtService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SystemConfigService systemConfigService;

    @Override
    public Page<UserDto> pagingUsers(UserSearchDto dto) {
        if (dto == null)
            return null;

        int pageIndex = dto.getPageIndex();
        int pageSize = dto.getPageSize();

        if (pageIndex > 0)
            pageIndex--;
        else
            pageIndex = 0;

        String whereClause = "";

        String orderBy = " ORDER BY entity.person.displayName DESC";

        String sqlCount = "select count(entity.id) from User as entity where (1=1)";
        String sql = "select new com.globits.security.dto.UserDto(entity) from User as entity where (1=1)";
        if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
            whereClause += " AND (entity.person.displayName LIKE :text OR entity.person.firstName LIKE :text OR entity.username LIKE :text ) ";
        }

        sql += whereClause + orderBy;
        sqlCount += whereClause + orderBy;

        Query q = manager.createQuery(sql, UserDto.class);
        Query qCount = manager.createQuery(sqlCount);

        if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
            q.setParameter("text", '%' + dto.getKeyword() + '%');
            qCount.setParameter("text", '%' + dto.getKeyword() + '%');
        }
        int startPosition = pageIndex * pageSize;
        q.setFirstResult(startPosition);
        q.setMaxResults(pageSize);
        List<UserDto> entities = q.getResultList();
        long count = (long) qCount.getSingleResult();

        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return new PageImpl<>(entities, pageable, count);
    }

    @Override
    public UserExtDto deleteById(Long userId) {
        if (userId == null || userId <= 0) {
            return null;
        }
        User user = userRepository.findById(userId).orElse(null);
        if (user != null && !user.getUsername().equals("admin")) {
            userRepository.delete(user);
            return new UserExtDto(user);
        } else {
            return null;
        }
    }

    @Override
    public List<UserExtDto> deleteListId(List<Long> userId) {
        if(userId==null || (userId!=null&& userId.size()<=0)) {
            return null;
        }
        List<UserExtDto> ret=new ArrayList<>();
        for (Long long1 : userId) {
            UserExtDto dto = deleteById(long1);
            if(dto!=null) {
                ret.add(dto);
            }
        }

        return ret;
    }

    @Override
    public ResponseEntity<UserKeyCloackDto> creatUserKeyCloak(UserDto dto) {
        if(dto!=null) {
            UserKeyCloackDto dtoKey = new UserKeyCloackDto();
            dtoKey.setCreatedTimestamp(new Date());
            dtoKey.setUsername(dto.getUsername());
            dtoKey.setEnabled(true);
            dtoKey.setTotp(false);
            dtoKey.setEmailVerified(true);
            if(dto.getPerson()!=null && dto.getPerson().getFirstName()!=null) {
                dtoKey.setFirstName(dto.getPerson().getFirstName());
            }
            else if(dto.getFirstName()!=null) {
                dtoKey.setFirstName(dto.getFirstName());
            }
            if(dto.getPerson()!=null && dto.getPerson().getLastName()!=null) {
                dtoKey.setLastName(dto.getPerson().getLastName());
            }
            else if(dto.getLastName()!=null) {
                dtoKey.setLastName(dto.getLastName());
            }
            if(dtoKey.getFirstName()==null && dtoKey.getLastName()==null && dto.getPerson().getDisplayName()!=null) {
                String[] output = dto.getPerson().getDisplayName().split("\\s", 0);
                String last="";
                String first="";
                if(output!=null && output.length==1) {
                    last=output[output.length-1];
                }
                else if(output!=null && output.length>1) {
                    last=output[output.length-1];

                    for (int i=0; i<output.length-1 ; i++) {
                        if(first.length()>0) {
                            first=first+" ";
                        }
                        first=first+output[i];
                    }
                }
                dtoKey.setLastName(last);
                dtoKey.setFirstName(first);
            }

            dtoKey.setEmail(dto.getEmail());
            dtoKey.setDisableCredentialTypes(new ArrayList<String>());
            dtoKey.setRequiredActions(new ArrayList<String>());
            dtoKey.setNotBefore(0);
            dtoKey.setAccess(new AccessDto());
            dtoKey.getAccess().setImpersonate(true);
            dtoKey.getAccess().setManage(true);
            dtoKey.getAccess().setManageGroupMembership(true);
            dtoKey.getAccess().setMapRoles(true);
            dtoKey.getAccess().setView(true);
            dtoKey.setRealmRoles(new ArrayList<String>());
            dtoKey.getRealmRoles().add("mb-user");
            dtoKey.setCredentials(new ArrayList<CredentialDto>());
            CredentialDto cDto=new CredentialDto();
            cDto.setType("password");
            cDto.setValue(dto.getPassword());
            dtoKey.getCredentials().add(cDto);
            String username="";String password="";String urlLogin="";String urlUser="";
            SystemConfigDto usernameKey = systemConfigService.getByKeyCode(EmrConstants.usernameKey);
            if(usernameKey!=null&& usernameKey.getConfigValue()!=null) {
                username=usernameKey.getConfigValue();
            }
            SystemConfigDto passwordKey = systemConfigService.getByKeyCode(EmrConstants.passwordKey);
            if(passwordKey!=null&& passwordKey.getConfigValue()!=null) {
                password=passwordKey.getConfigValue();
            }
            SystemConfigDto urlLoginKey = systemConfigService.getByKeyCode(EmrConstants.urlLogin);
            if(urlLoginKey!=null&&urlLoginKey.getConfigValue()!=null) {
                urlLogin=urlLoginKey.getConfigValue();
            }
            SystemConfigDto urlUserKey = systemConfigService.getByKeyCode(EmrConstants.urlUser);
            if(urlUserKey!=null&& urlUserKey.getConfigValue()!=null) {
                urlUser=urlUserKey.getConfigValue();
            }
//			RestApiUtils.postLogin();
            ResponseEntity<UserKeyCloackDto> keyUser = RestApiUtils.post(username,password,urlLogin,urlUser, dtoKey,UserKeyCloackDto.class);
            return keyUser;
        }

        return null;
    }

    @Override
    public UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = null;
        jwt = (Jwt)authentication.getPrincipal();
        String userName = null;
        if(jwt != null && jwt.getClaims()!=null && jwt.getClaims().get("preferred_username")!=null) {
            userName = jwt.getClaims().get("preferred_username").toString();
        }
        User modifiedUser = userRepository.findByUsernameAndPerson(userName);
        if(modifiedUser!=null) {
            return new UserDto(modifiedUser);
        }
        return null;
    }
}
