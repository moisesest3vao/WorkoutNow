//package com.workoutnow.general.models;
//
//import com.workoutnow.general.converter.StringListConverter;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.keycloak.representations.idm.UserRepresentation;
//
//import javax.persistence.Convert;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import java.util.List;
//
//@Entity
//@Data
//@Table(name = "tb_user")
//@NoArgsConstructor
//public class User {
//    @Id
//    protected String id;
//    protected String username;
//    protected Boolean enabled;
//    protected Boolean emailVerified;
//    protected String firstName;
//    protected String lastName;
//    protected String email;
//    @Convert(converter = StringListConverter.class)
//    protected List<String> realmRoles;
//
//    public User(UserRepresentation userRepresentation) {
//        this.id = userRepresentation.getId();
//        this.enabled = userRepresentation.isEnabled();
//        this.username = userRepresentation.getUsername();
//        this.emailVerified = userRepresentation.isEmailVerified();
//        this.firstName = userRepresentation.getFirstName();
//        this.firstName = userRepresentation.getFirstName();
//        this.lastName = userRepresentation.getLastName();
//        this.email = userRepresentation.getEmail();
//        this.realmRoles = userRepresentation.getRealmRoles();
//    }
//}
