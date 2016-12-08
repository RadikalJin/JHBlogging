package com.jdh.user.util;

import com.jdh.user.domain.User;
import com.jdh.user.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class UserUtil {

    public static UserDto convertUserDomainToDto(User user) {
        UserDto userDto = new UserDto();
        if (user.getId() != null) {
            userDto.setId(String.valueOf(user.getId()));
        }
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setEmailAddress(user.getEmailAddress());
        return userDto;
    }


    public static List<UserDto> convertUserDomainsToDtos(List<User> userDomains) {
        List<UserDto> userDtos = new ArrayList<>();
        for (User userDomain : userDomains) {
            userDtos.add(convertUserDomainToDto(userDomain));
        }
        return userDtos;
    }

    public static User convertUserDtoToDomain(UserDto userDto) {
        User user = new User();
        if (userDto.getId() != null) {
            user.setId(Integer.parseInt(userDto.getId()));
        }
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmailAddress(userDto.getEmailAddress());
        return user;
    }

    public static List<User> convertUserDtosToDomains(List<UserDto> userDtos) {
        List<User> users = new ArrayList<>();
        for (UserDto userDto : userDtos) {
            users.add(convertUserDtoToDomain(userDto));
        }
        return users;
    }


}
