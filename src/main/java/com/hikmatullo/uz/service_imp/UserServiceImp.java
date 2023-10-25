package com.hikmatullo.uz.service_imp;

import com.hikmatullo.uz.dto.UserDto;
import com.hikmatullo.uz.entity.User;
import com.hikmatullo.uz.repository.UserRepository;
import com.hikmatullo.uz.service.UserService;
import com.hikmatullo.uz.util.RegisterValidation;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String save(UserDto dto) {
        String message = RegisterValidation.isPasswordValid(dto);
        if(isEmailAuthenticated(dto.getEmail())) {
            message = "email is already registered";
        }
        else if(message == null) {
            User user = makeUser(dto);
            userRepository.save(user);
        }
        return message;
    }

    private boolean isEmailAuthenticated(String email) {
        User byEmail = userRepository.findByEmail(email);
        if(byEmail == null) {
            return false;
        }
        return true;
    }

    private User makeUser(UserDto dto) {
        User user = new User();
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());
        return user;
    }

}
