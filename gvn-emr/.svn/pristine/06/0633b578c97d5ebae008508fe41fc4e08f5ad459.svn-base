package com.globits.emr.rest;

import com.globits.emr.EmrConstants;
import com.globits.emr.dto.UserExtDto;
import com.globits.emr.dto.loginkeycloak.ObjectDto;
import com.globits.emr.dto.loginkeycloak.UserKeyCloackDto;
import com.globits.emr.dto.search.UserSearchDto;
import com.globits.emr.service.UserExtService;
import com.globits.security.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/user-ext")
public class RestUserExtController {
    @Autowired
    private UserExtService service;

    @Secured({ EmrConstants.ROLE_ADMIN})
    @RequestMapping(value = "/paging-users", method = RequestMethod.POST)
    public ResponseEntity<Page<UserDto>> pagingUsers(@RequestBody UserSearchDto dto) {
        Page<UserDto> result = service.pagingUsers(dto);
        return new ResponseEntity<Page<UserDto>>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Secured({ EmrConstants.ROLE_ADMIN})
    @RequestMapping(value = "/delete-list", method = RequestMethod.DELETE)
    public List<UserExtDto> deleteListId(@RequestBody List<Long> userIds) {
        return service.deleteListId(userIds);
    }

    @Secured({ EmrConstants.ROLE_ADMIN})
    @RequestMapping(value = "/delete-by-id/{id}", method = RequestMethod.DELETE)
    public UserExtDto deleteListId(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @Secured({ EmrConstants.ROLE_ADMIN})
    @RequestMapping(value = "/get-current-user-name", method = RequestMethod.GET)
    public ResponseEntity<String> getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = null;
        jwt = (Jwt) authentication.getPrincipal();
        String userName = null;
        if (jwt.getClaims() != null && jwt.getClaims().get("preferred_username") != null) {
            userName = jwt.getClaims().get("preferred_username").toString();
        }
        return new ResponseEntity<>(userName, userName != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Secured({ EmrConstants.ROLE_ADMIN})
    @RequestMapping(value = "/get-current-principal", method = RequestMethod.GET)
    public ResponseEntity<Jwt> getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = null;
        jwt = (Jwt) authentication.getPrincipal();
//		String userName = null;
//		if(jwt.getClaims()!=null && jwt.getClaims().get("preferred_username")!=null) {
//			userName = jwt.getClaims().get("preferred_username").toString();
//		}
        return new ResponseEntity<>(jwt, jwt != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Secured({ EmrConstants.ROLE_ADMIN})
    @RequestMapping(value = "/create-use-keycloak", method = RequestMethod.POST)
    public ObjectDto createUserKeyCloak(@RequestBody UserDto dto) {
        ObjectDto ret = new ObjectDto();
        ResponseEntity<UserKeyCloackDto> s = null;
        try {
            s = service.creatUserKeyCloak(dto);
            ret.setCode(String.valueOf(s.getStatusCodeValue()));
//			 ret.setNote(s.);
        } catch (Exception e) {
            ret.setIsFaled(true);
            String code = e.getMessage();
            if (code != null && code.length() > 0) {
                String[] statuss = code.split(":");
                if (statuss != null && statuss.length > 0) {
                    ret.setCode(statuss[0].trim());
                    if (statuss.length >= 2) {
                        String note = statuss[2].trim();
                        note = note.replaceAll("}", "");
                        ret.setNote(note);
                    } else if (statuss.length >= 1) {
                        String note = statuss[1].trim();
                        note = note.replaceAll("}", "");
                        ret.setNote(note);
                    }

                }
                return ret;
            }
            // TODO: handle exception
        }

        return ret;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/get-current-user")
    public UserDto getCurrentUser() {
        UserDto user = service.getCurrentUser();

        if (user != null) {
            user.setPassword(null);
        }

        return user;
    }
}
