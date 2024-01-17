package com.usermanager.controller;

import com.usermanager.models.UserModel;
import com.usermanager.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    private final UserRepository _userRepository;

    public UserController(UserRepository userRepository)
    {
        _userRepository = userRepository;
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<UserModel>> findAllUsers()
    {
        List<UserModel> users = _userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<UserModel> findUserById(@PathVariable Long id)
    {
        return _userRepository.findById(id).map(ResponseEntity :: ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Void> saveUser(@RequestBody UserModel user)
    {
        _userRepository.save(user);

        String createdResourceUrl = "/api/resources/{id}";
        URI uri = URI.create(createdResourceUrl);

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserModel> update(@PathVariable Long id, @RequestBody UserModel user)
    {
        if (!_userRepository.existsById(id))
            return ResponseEntity.notFound().build();
        
        user.setId(id);
        user = _userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        if (!_userRepository.existsById(id))
            return ResponseEntity.notFound().build();

        _userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
