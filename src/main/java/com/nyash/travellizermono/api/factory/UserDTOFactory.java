package com.nyash.travellizermono.api.factory;

import com.nyash.travellizermono.api.dto.UserDTO;
import com.nyash.travellizermono.api.entity.user.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDTOFactory {

    public UserDTO createUserDTO(UserEntity entity) {
        return UserDTO.builder()
                .id(entity.getId())
                .userName(entity.getUserName())
                .password(entity.getPassword())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public List<UserDTO> createUserDTOList(List<UserEntity> entities) {
        return entities
                .stream()
                .map(this::createUserDTO)
                .collect(Collectors.toList());
    }
}
