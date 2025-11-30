package com.domain.profile.app.user.service.impl;

import com.domain.mapper.service.MapperService;
import com.domain.profile.app.user.dto.UserDto;
import com.domain.profile.app.user.entity.UserEntity;
import com.domain.profile.app.user.repository.UserRepository;
import com.domain.profile.app.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final MapperService mapper;

    @Override
    public List<UserDto> findAll() {
        try {
            List<UserEntity> entities = repository.findAll();
            return entities.stream().map(e -> (UserDto) mapper.toDto(e)).toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDto findById(Long id) {
        try {
            Optional<UserEntity> entity = repository.findById(id);
            if (entity.isEmpty()) {
                throw new EntityNotFoundException("User with id %d is not found".formatted(id));
            }
            return (UserDto) mapper.toDto(entity.get());
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDto findByEmail(String email) {
        try {
            Optional<UserEntity> entity = repository.findByEmail(email);
            if (entity.isEmpty()) {
                throw new EntityNotFoundException("User with email %s not found".formatted(email));
            }
            return (UserDto) mapper.toDto(entity.get());
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public UserDto create(UserDto dto) {
        try {
            UserEntity entity = (UserEntity) mapper.toEntity(dto);
            UserEntity saved = repository.save(entity);
            return (UserDto) mapper.toDto(saved);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public UserDto update(UserDto dto) {
        try {
            Optional<UserEntity> og = repository.findById(dto.getId());
            if (og.isEmpty()) {
                throw new EntityNotFoundException("User with id %s not found".formatted(dto.getId()));
            }
            UserEntity nu = (UserEntity) mapper.toEntity(dto);
            UserEntity merged = (UserEntity) mapper.merge(og.get(), nu);
            UserEntity saved = repository.save(merged);
            return (UserDto) mapper.toDto(saved);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public UserDto delete(UserDto dto) {
        try {
            Optional<UserEntity> entity = repository.findById(dto.getId());
            if (entity.isEmpty()) {
                throw new EntityNotFoundException("User with id %s not found".formatted(dto.getId()));
            }
            repository.delete(entity.get());
            return dto;
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
