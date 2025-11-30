package com.domain.profile.app.user.service;

import com.domain.profile.app.user.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();

    UserDto findById(Long id);

    UserDto findByEmail(String email);

    UserDto create(UserDto dto);

    UserDto update(UserDto dto);

    UserDto delete(UserDto dto);
}
