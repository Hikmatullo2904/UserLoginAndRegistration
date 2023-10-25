package com.hikmatullo.uz.util;

import com.hikmatullo.uz.dto.UserDto;

public class RegisterValidation {
    public static String isPasswordValid(UserDto dto) {
        String password = dto.getPassword();
        String message = null;
        if(!isPasswordLengthValid(password)) {
            message = "The length should be at least 8 characters";
        } else if(!isPasswordValid(password)) {
            message = "the password should contain both letters and numbers";
        }
        return message;
    }

    private static boolean isPasswordLengthValid(String password) {
        return password.length() > 7;
    }

    private static boolean isPasswordValid(String password) {
        boolean hasChars = false, hasNums = false;
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                hasChars = true;
            } else if (Character.isDigit(c)) {
                hasNums = true;
            }
        }
        return hasChars && hasNums;
    }
}
