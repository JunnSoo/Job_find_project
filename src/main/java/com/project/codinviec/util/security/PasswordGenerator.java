package com.project.codinviec.util.security;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
public class PasswordGenerator {

    private final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private final String DIGIT = "0123456789";
    private final String SPECIAL = "!@#$%^&*()-_=+<>?";

    private  final String ALL = UPPER + LOWER + DIGIT + SPECIAL;

    private  final SecureRandom random = new SecureRandom();

    public  String generatePassword(int length) {
        if (length < 4) throw new IllegalArgumentException("Length must be >= 4");

        StringBuilder password = new StringBuilder();

        // đảm bảo mỗi loại ít nhất 1 ký tự
        password.append(getRandomChar(UPPER));
        password.append(getRandomChar(LOWER));
        password.append(getRandomChar(DIGIT));
        password.append(getRandomChar(SPECIAL));

        // fill các ký tự còn lại
        for (int i = 4; i < length; i++) {
            password.append(getRandomChar(ALL));
        }

        // trộn random để không cố định vị trí 4 ký tự đầu
        List<Character> chars = password.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        Collections.shuffle(chars, random);

        return chars.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private char getRandomChar(String src) {
        return src.charAt(random.nextInt(src.length()));
    }

    public void main(String[] args) {
        System.out.println(generatePassword(12));
    }
}