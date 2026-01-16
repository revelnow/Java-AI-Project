

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class UserService {

    private static final DateTimeFormatter BIRTH_FMT = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final String INVALID = "无效的身份证号码";

    public int getAge(String idCard) {
        LocalDate birth = parseBirth(idCard);
        LocalDate today = LocalDate.now();
        if (birth.isAfter(today)) {
            throw new IllegalArgumentException(INVALID);
        }
        return Period.between(birth, today).getYears();
    }

    public String getGender(String idCard) {
        String s = normalize(idCard);
        // 第17位（索引16）判断奇偶；这里只允许数字（不处理校验码X）
        char c = s.charAt(16);
        if (!Character.isDigit(c)) throw new IllegalArgumentException(INVALID);

        int n = c - '0';
        return (n % 2 == 1) ? "男" : "女";
    }

    private LocalDate parseBirth(String idCard) {
        String s = normalize(idCard);
        String birthday = s.substring(6, 14);
        try {
            return LocalDate.parse(birthday, BIRTH_FMT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(INVALID, e);
        }
    }

    private String normalize(String idCard) {
        if (idCard == null) throw new IllegalArgumentException(INVALID);
        String s = idCard.trim();
        if (s.length() != 18) throw new IllegalArgumentException(INVALID);

        // 可选：前17位必须是数字（若你要支持末位X，单独处理第18位）
        for (int i = 0; i < 17; i++) {
            if (!Character.isDigit(s.charAt(i))) throw new IllegalArgumentException(INVALID);
        }
        return s;
    }
}
