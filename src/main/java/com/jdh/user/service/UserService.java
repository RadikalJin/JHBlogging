package com.jdh.user.service;

import com.jdh.user.dto.UserDto;

import java.util.List;

public interface UserService {

    public List<UserDto> getAllUsers() throws Exception;
    public void persistUser(UserDto userDto) throws Exception;
    public void persistUsers(List<UserDto> users) throws Exception;
    public UserDto getUserById(Integer userId) throws Exception;
    public UserDto getUserByUsername(String userName);
    public void deleteUserById(Integer userId);
    public void updateUser(final UserDto userDto);
    public void deleteAllUsers();

}
