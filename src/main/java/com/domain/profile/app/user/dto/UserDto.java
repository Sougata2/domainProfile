package com.domain.profile.app.user.dto;

import com.domain.mapper.references.MasterDto;
import com.domain.profile.app.role.dto.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for {@link com.domain.profile.app.user.entity.UserEntity}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable, MasterDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<RoleDto> roles;
    private RoleDto defaultRole;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}