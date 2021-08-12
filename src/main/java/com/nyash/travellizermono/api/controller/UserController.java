package com.nyash.travellizermono.api.controller;

import com.nyash.travellizermono.api.common.infra.util.StringChecker;
import com.nyash.travellizermono.api.dto.UserDTO;
import com.nyash.travellizermono.api.entity.user.UserEntity;
import com.nyash.travellizermono.api.entity.user.UserRole;
import com.nyash.travellizermono.api.factory.UserDTOFactory;
import com.nyash.travellizermono.api.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@ExtensionMethod(StringChecker.class)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Controller
@Transactional
public class UserController {

    UserRepository userRepository;

    UserDTOFactory userDTOFactory;

    public static final String FETCH_USERS = "api/users";
    public static final String CREATE_USER = "api/users";

    @GetMapping(FETCH_USERS)
    public ResponseEntity<List<UserDTO>> fetchUsers(
            @RequestParam(defaultValue = "") String filter) {

        boolean isFiltered = !filter.trim().isEmpty();

        List<UserEntity> users = userRepository.findAllByFilter(isFiltered, filter);

        return ResponseEntity.ok(userDTOFactory.createUserDTOList(users));
    }

    @PostMapping(CREATE_USER)
    public ResponseEntity<UserDTO> createUser(
            @RequestParam String userName,
            @RequestParam String password,
            @RequestParam String firstName,
            @RequestParam String lastName,
//            @RequestParam String registrationIp, //TODO: user registration IP
            @RequestParam UserRole userRole) {

        firstName = firstName.trim();
        lastName = lastName.trim();
        password = password.trim();
        userName = userName.trim();

        firstName.checkOnEmpty("firstName");
        lastName.checkOnEmpty("lastName");
        password.checkOnEmpty("password");
        userName.checkOnEmpty("userName");


        UserEntity user = userRepository.saveAndFlush(
                UserEntity.makeDefault(
                        userName,
                        password,
                        firstName,
                        lastName,
                        userRole
                )
        );

        return ResponseEntity.ok(userDTOFactory.createUserDTO(user));
    }
}
