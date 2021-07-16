package com.abdul.library.books.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence plainTextPassword) {
    	String pass = plainTextPassword.toString();
        return "A" + pass + "b";
    }

    @Override
    public boolean matches(CharSequence plainTextPassword, String passwordInDatabase) {
        return encode(plainTextPassword).equals(passwordInDatabase);
    }
}
