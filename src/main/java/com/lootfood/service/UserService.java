package com.lootfood.service;

import com.lootfood.entity.User;
import com.lootfood.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User add(User user) {
        return userRepository.save(user);
    }

    public User getById(String id) {
        return userRepository.findById(id).get();
    }

    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User update(User user) {
        return userRepository.update(user);
    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }
}
