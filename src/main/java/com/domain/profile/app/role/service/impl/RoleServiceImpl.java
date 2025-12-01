package com.domain.profile.app.role.service.impl;

import com.domain.mapper.service.MapperService;
import com.domain.profile.app.role.dto.RoleDto;
import com.domain.profile.app.role.entity.RoleEntity;
import com.domain.profile.app.role.repository.RoleRepository;
import com.domain.profile.app.role.service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;
    private final MapperService mapper;

    @Override
    public List<RoleDto> findAll() {
        try {
            List<RoleEntity> entities = repository.findAll();
            return entities.stream().map(e -> (RoleDto) mapper.toDto(e)).toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RoleDto findByName(String name) {
        try {
            Optional<RoleEntity> entity = repository.findByName(name);
            if (entity.isEmpty()) {
                throw new EntityNotFoundException("Role with name %s is not found".formatted(name));
            }
            return (RoleDto) mapper.toDto(entity.get());
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RoleDto findById(Long id) {
        try {
            Optional<RoleEntity> entity = repository.findById(id);
            if (entity.isEmpty()) {
                throw new EntityNotFoundException("Role with id %d is not found".formatted(id));
            }
            return (RoleDto) mapper.toDto(entity.get());
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public RoleDto create(RoleDto dto) {
        try {
            RoleEntity entity = (RoleEntity) mapper.toEntity(dto);
            RoleEntity saved = repository.save(entity);
            return (RoleDto) mapper.toDto(saved);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public RoleDto update(RoleDto dto) {
        try {
            Optional<RoleEntity> og = repository.findById(dto.getId());
            if (og.isEmpty()) {
                throw new EntityNotFoundException("Role with id %d is not found".formatted(dto.getId()));
            }
            RoleEntity nu = (RoleEntity) mapper.toEntity(dto);
            RoleEntity merged = (RoleEntity) mapper.merge(og.get(), nu);
            if (!merged.getName().startsWith("ROLE_")) {
                merged.setName("ROLE_" + merged.getName().toUpperCase());
            }
            RoleEntity saved = repository.save(merged);
            return (RoleDto) mapper.toDto(saved);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public RoleDto delete(RoleDto dto) {
        try {
            Optional<RoleEntity> og = repository.findById(dto.getId());
            if (og.isEmpty()) {
                throw new EntityNotFoundException("Role with id %d is not found".formatted(dto.getId()));
            }
            repository.delete(og.get());
            return dto;
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
