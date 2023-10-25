package com.hikmatullo.uz.service;

import com.hikmatullo.uz.dto.UserDto;
import com.hikmatullo.uz.entity.User;

public interface UserService {
    String save(UserDto dto);
}
