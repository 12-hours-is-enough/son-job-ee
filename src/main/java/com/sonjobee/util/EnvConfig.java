package com.sonjobee.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EnvConfig {
    private static final Map<String, String> envVariables = new HashMap<>();

    static {
        try (InputStream input = EnvConfig.class.getClassLoader().getResourceAsStream(".env")) {
            if (input == null) {
                System.err.println("⚠️ .env 파일을 찾을 수 없습니다!");
            } else {
                try (Scanner scanner = new Scanner(input, StandardCharsets.UTF_8.name())) {
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine().trim();
                        if (!line.isEmpty() && line.contains("=")) {
                            String[] parts = line.split("=", 2);
                            envVariables.put(parts[0].trim(), parts[1].trim());
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("⚠️ .env 파일을 불러오는 중 오류 발생: " + e.getMessage());
        }
    }

    public static String get(String key) {
        return envVariables.get(key);
    }
}
