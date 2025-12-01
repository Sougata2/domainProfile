package com.domain.profile.app.role.service;

import com.domain.profile.app.role.dto.RoleDto;

import java.util.List;

public interface RoleService {
    List<RoleDto> findAll();

    RoleDto findByName(String name);

    RoleDto findById(Long id);

    RoleDto create(RoleDto dto);

    RoleDto update(RoleDto dto);

    RoleDto delete(RoleDto dto);
}
