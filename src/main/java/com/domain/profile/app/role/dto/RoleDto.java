package com.domain.profile.app.role.dto;

import com.domain.mapper.references.MasterDto;
import com.domain.profile.app.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for {@link com.domain.profile.app.role.entity.RoleEntity}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto implements Serializable, MasterDto {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<UserDto> users;
    private Set<UserDto> defaultRoleUsers;
}