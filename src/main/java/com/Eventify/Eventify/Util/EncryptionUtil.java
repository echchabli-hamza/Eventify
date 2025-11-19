package com.Eventify.Eventify.Util;

import org.springframework.stereotype.Component;

@Component
public class EncryptionUtil {

    private final String secret = "MySimpleKey"; // simple key

    // Simple XOR encryption
    public String encrypt(String input) {
        char[] key = secret.toCharArray();
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] ^= key[i % key.length];
        }
        return new String(chars);
    }

    public String decrypt(String input) {
        // XOR is reversible with same operation
        return encrypt(input);
    }
}
