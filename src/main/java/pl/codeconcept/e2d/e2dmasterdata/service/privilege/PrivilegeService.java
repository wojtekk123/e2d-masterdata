package pl.codeconcept.e2d.e2dmasterdata.service.privilege;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.AuthEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.UserEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.enums.UserType;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.AuthRepo;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.UserRepo;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrivilegeService {


    private final AuthRepo authRepo ;
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

        Optional<AuthEntity> byId = authRepo.findById(getAuthId());
        AuthEntity authEntity =  byId.orElseThrow(IllegalArgumentException::new);
        return authEntity.getUserEntity();
    }


    private  String getRole() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return authorities.iterator().next().getAuthority();

    }

    private  Long getAuthId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (Long) authentication.getCredentials();
    }
}