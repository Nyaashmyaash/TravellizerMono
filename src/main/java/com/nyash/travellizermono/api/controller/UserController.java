package com.nyash.travellizermono.api.controller;

import com.nyash.travellizermono.api.common.infra.exception.NoSuchUserException;
import com.nyash.travellizermono.api.common.infra.util.StringChecker;
import com.nyash.travellizermono.api.dto.AckDTO;
import com.nyash.travellizermono.api.dto.UserDTO;
import com.nyash.travellizermono.api.entity.user.UserEntity;
import com.nyash.travellizermono.api.entity.user.UserRole;
import com.nyash.travellizermono.api.factory.UserDTOFactory;
import com.nyash.travellizermono.api.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

/**
 * {@link UserController} is REST controller that handles user management requests
 *
 * @author Nyash
 */
@RequiredArgsConstructor
@ExtensionMethod(StringChecker.class)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Controller
@Transactional
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    //TODO: add authorise

    UserRepository userRepository;

    /**
     * City DTO <-> Entity transformation
     */
    UserDTOFactory userDTOFactory;

    /**
     * Endpoints
     */
    public static final String FETCH_USERS = "api/users";
    public static final String CREATE_USER = "api/users";
    public static final String SHOW_USER = "api/users/{userId}";
    public static final String UPDATE_USER = "api/users/{userId}";
    public static final String DELETE_USER = "api/users/{userId}";
    //TODO: feature that promote user to Manager

    /**
     * Returns all users by filter
     *
     * @param filter
     * @return
     */
    @GetMapping(FETCH_USERS)
    public ResponseEntity<List<UserDTO>> fetchUsers(
            @RequestParam(defaultValue = "") String filter) {

        boolean isFiltered = !filter.trim().isEmpty();

        List<UserEntity> users = userRepository.findAllByFilter(isFiltered, filter);

        return ResponseEntity.ok(userDTOFactory.createUserDTOList(users));
    }

    /**
     * Creates new user
     *
     * @param userName
     * @param password
     * @param firstName
     * @param lastName
     * @param userRole
     * @return
     */
    @PostMapping(CREATE_USER)
    public ResponseEntity<UserDTO> createUser(
            @RequestParam String userName,
            @RequestParam String password,
            @RequestParam String firstName,
            @RequestParam String lastName,
//            @RequestParam String registrationIp,
            @RequestParam UserRole userRole) {

        //TODO: user registration IP

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

    /**
     * Returns user by ID
     *
     * @param userId
     * @return
     */
    @GetMapping(SHOW_USER)
    public ResponseEntity<UserDTO> showUser(@PathVariable Long userId) {

        UserEntity user = userRepository.findById(userId).orElseThrow(NoSuchUserException::new);

        return ResponseEntity.ok(userDTOFactory.createUserDTO(user));
    }

    @PostMapping(UPDATE_USER)
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId,
                                              @RequestParam String userName,
                                              @RequestParam String password,
                                              @RequestParam String firstName,
                                              @RequestParam String lastName,
                                              @RequestParam UserRole userRole) {

        UserEntity user = userRepository.findById(userId).orElseThrow(NoSuchUserException::new);

        user.setUserName(userName);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserRole(userRole);

        UserEntity updatedUser = userRepository.saveAndFlush(user);

        return ResponseEntity.ok(userDTOFactory.createUserDTO(updatedUser));
    }

    /**
     * Delete user
     *
     * @param userId
     * @return
     */
    @DeleteMapping(DELETE_USER)
    public ResponseEntity<AckDTO> deleteUser(
            @PathVariable Long userId) {

        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        }

        LOG.warn("User with id: " + userId + " was deleted");

        return ResponseEntity.ok(AckDTO.makeDefault(true));
    }
}
