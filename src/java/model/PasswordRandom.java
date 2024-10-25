/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.security.SecureRandom;
import java.util.Random;

/**
 *
 * @author BUI TUAN DAT
 */
public class PasswordRandom {

    private static final Random RANDOM = new SecureRandom();
    private static final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String generateRandomPassword(int minLength, int maxLength) {
        if (minLength > maxLength || minLength < 1 || maxLength > 14) {
            throw new IllegalArgumentException("Invalid min/max length for password.");
        }
        int passwordLength = RANDOM.nextInt(maxLength - minLength + 1) + minLength;
        StringBuilder sb = new StringBuilder(passwordLength);
        for (int i = 0; i < passwordLength; i++) {
            sb.append(ALLOWED_CHARACTERS.charAt(RANDOM.nextInt(ALLOWED_CHARACTERS.length())));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String password = generateRandomPassword(6, 14);
        System.out.println("Generated Password: " + password);
    }
}
