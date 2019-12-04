package pl.codeconcept.e2d.e2dmasterdata.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.UserEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.enums.UserType;
import pl.codeconcept.e2d.e2dmasterdata.service.privilege.PrivilegeService;
@Service

public abstract class AbstractApi {

    @Autowired
    private  PrivilegeService privilegeService;

    protected UserEntity getUserFromToken() {
        UserType userType = privilegeService.whoIAm();
        return privilegeService.getUser();

    }
}