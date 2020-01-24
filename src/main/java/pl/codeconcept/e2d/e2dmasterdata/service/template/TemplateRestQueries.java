package pl.codeconcept.e2d.e2dmasterdata.service.template;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.codeconcept.e2d.e2dmasterdata.database.enums.UserType;
import pl.codeconcept.e2d.e2dmasterdata.model.Auth;
import pl.codeconcept.e2d.e2dmasterdata.model.AuthBack;

import java.rmi.ConnectIOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class TemplateRestQueries {

    private final RestTemplate restTemplate;

    public AuthBack getUserData(String token, Auth auth, UserType userType) throws ConnectIOException, NullPointerException{
        final String uri = "http://cloudauth:8081/signup";
        auth.setRole(userType.toString());
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Auth> requestEntity = new HttpEntity<>(auth,headers);
        AuthBack authBack = restTemplate.postForObject(uri, requestEntity, AuthBack.class);
        log.info("you receive data like"+ authBack);
        return authBack;
    }
}
