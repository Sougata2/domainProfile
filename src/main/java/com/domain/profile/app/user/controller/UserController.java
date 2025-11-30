package com.domain.profile.app.user.controller;

import com.domain.profile.app.user.dto.UserDto;
import com.domain.profile.app.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(service.findByEmail(email));
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(service.create(userDto));
    }

    @PutMapping
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(service.update(userDto));
    }

    @DeleteMapping
    public ResponseEntity<UserDto> delete(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(service.delete(userDto));
    }
}
