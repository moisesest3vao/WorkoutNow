package com.workoutnow.general.service;

import com.workoutnow.general.config.keycloak.KeycloakManager;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.Response;

@Service
@NoArgsConstructor
@Slf4j
public class KeycloakService {

    @Autowired
    private KeycloakManager keyCloakManager;
    @Autowired
    private RestTemplate restTemplate;

    public Integer createUser(UserRepresentation userRepresentation) {
        Response response = keyCloakManager.getKeyCloakInstanceWithRealm().users().create(userRepresentation);
        return response.getStatus();
    }

    public void updateUser(UserRepresentation userRepresentation) {
        keyCloakManager.getKeyCloakInstanceWithRealm().users().get(userRepresentation.getId()).update(userRepresentation);
    }

    public UserRepresentation readUserById(String id) {
        return keyCloakManager.getKeyCloakInstanceWithRealm().users().get(id).toRepresentation();
    }
}
