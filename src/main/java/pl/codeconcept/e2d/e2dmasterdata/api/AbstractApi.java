package pl.codeconcept.e2d.e2dmasterdata.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.UserEntity;
import pl.codeconcept.e2d.e2dmasterdata.service.privilege.PrivilegeService;

@Service

public abstract class AbstractApi {

    @Autowired
    private PrivilegeService privilegeService;

    UserEntity getUserFromToken() {
        return privilegeService.getUser();
    }
}