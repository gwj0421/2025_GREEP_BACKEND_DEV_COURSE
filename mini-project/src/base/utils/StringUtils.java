package base.utils;

import base.exception.IncorrectInputException;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringUtils {
    private StringUtils() {
    }

    public static String trimPrefix(String input, String prefix) {
        if (canTrim(input, prefix) && input.startsWith(prefix)) {
            return input.substring(prefix.length());
        }
        throw new IncorrectInputException();
    }

    public static String trimSuffix(String input, String suffix) {
        if (canTrim(input, suffix) && input.endsWith(suffix)) {
            return input.substring(0, input.length() - suffix.length());
        }
        throw new IncorrectInputException();
    }

    private static boolean canTrim(String input, String word) {
        return input.length() >= word.length();
    }

    // number => Long
    public static Long parseLong(String num) {
        try {
            return Long.parseLong(num);
        } catch (NumberFormatException e) {
            throw new IncorrectInputException();
        }
    }

    // 게시글 본문을 빈 공백을 입력할때 까지 입력
    public static String toContent(BufferedReader br) {
        try {
            StringBuilder sb = new StringBuilder();
            while (true) {
                String input = br.readLine();
                if (input.isBlank()) {
                    break;
                }
                sb.append(input).append('\n');
            }
            return sb.toString();
        } catch (IOException e) {
            throw new IncorrectInputException();
        }
    }

    public static String toDate(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("yy/MM/dd"));
    }
}
