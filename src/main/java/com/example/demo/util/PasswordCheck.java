package com.example.demo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordCheck {

    public static void main(String[] args) {
        String raw = "demo123";
        String encoded = new BCryptPasswordEncoder().encode(raw);
        System.out.println("新しいハッシュ: " + encoded);
    }
}