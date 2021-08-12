package com.nyash.travellizermono.api.controller;

import com.nyash.travellizermono.api.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Controller
@Transactional
public class UserController {

    UserRepository userRepository;


}
