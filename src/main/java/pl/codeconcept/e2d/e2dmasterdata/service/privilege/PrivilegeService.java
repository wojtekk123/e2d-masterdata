package pl.codeconcept.e2d.e2dmasterdata.service.privilege;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.UserEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.enums.UserType;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.UserRepo;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class PrivilegeService {


    private final UserRepo userRepo;

    public UserType whoIAm() {
        switch (getRole().toLowerCase().substring(5)) {
            case "student":
                return UserType.STUDENT;
            case "school":
                return UserType.SCHOOL;
            case "admin":
                return UserType.ADMIN;
            default:
                return null;
        }
    }

    public UserEntity getUser() {
        return userRepo.findByAuthId(getAuthId());
    }


    private String getRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return authorities.iterator().next().getAuthority();
    }

    private Long getAuthId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (Long) authentication.getCredentials();
    }
}