package ua.nure.odnokozov.railway.ticket.office.util;

/**
 * @author Maksym Odnokozov
 */
public class Validator {

    private static final String REG_EX_LATIN_WORD = "[A-Za-z]+";
    private static final String REG_EX_CYRILLIC_WORD = "[А-ЯЁа-яё]+";
    private static final String REG_EX_NUMBER = "[0-9]+";
    private static final String REG_EX_DOUBLE = "[0-9,\\.]+";
    private static final String REG_EX_DATE = "[0-9-:\\.]+";
    private static final String REG_EX_PASSWORD = "[A-Za-z0-9]+";    
    private static final String REX_EX_EMAIL_ADDRESS = "^\\w[\\w.]{1,63}@[\\w]{1,63}([.]{1}[\\w]{1,63})+";
    private static final int MAX_EMAIL_ADDRESS_LENGTH = 320;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int DATE_LENGTH = 10;

    /**
     * Return {@code true} if the argument is not null or length of argument more
     * that 0.
     * 
     * @param inputData a String
     * @return {@code true} if the argument is not null or length of argument more
     *         that 0. If the argument is not null, then method run {@code trim()}
     *         for inputData.
     */
    public static boolean isFilled(String inputData) {
        if (inputData == null || inputData.trim().length() == 0) {
            return false;
        }
        return true;
    }

    /**
     * Return {@code true} if the argument is not null or length of argument more
     * that 0 and less that maxLength.
     * 
     * @param inputData a String
     * @param maxLength an int
     * @return {@code true} if the argument is not null or length of argument more
     *         that 0. If the argument is not null, then method run {@code trim()}
     *         for inputData and checks inputData length, which must be from 1 to
     *         {@code maxLength}
     */
    public static boolean isFilled(String inputData, int maxLength) {
        if (inputData == null || inputData.trim().length() == 0 || inputData.trim().length() > maxLength) {
            return false;
        }
        return true;
    }

    /**
     * Return {@code true} if the argument consists only from characters of Latin
     * alphabet.
     * 
     * @param inputData a String
     * @return {@code true} if the argument consists only from characters of Latin
     *         alphabet. Method uses regular expression for validating string and
     *         {@code isFilled(String arg)} method. If string is more that one word,
     *         than break a string into words and validating every word. If some
     *         word of string is not consists from characters of Latin alphabet,
     *         then return {@code false}.
     */
    public static boolean isLatinWord(String inputData) {
        if (!isFilled(inputData)) {
            return false;
        } else {
            String[] words = inputData.split(" ");
            for (String word : words) {
                if (!word.matches(REG_EX_LATIN_WORD)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Return {@code true} if the argument consists only from characters of Cyrillic
     * alphabet.
     * 
     * @param inputData a String
     * @return {@code true} if the argument consists only from characters of
     *         Cyrillic alphabet. Method uses regular expression for validating
     *         string and {@code isFilled(String arg)} method. If string consists
     *         more than one word, then the string break into words and validating
     *         every word. If some word of string is not consists from characters of
     *         Cyrillic alphabet, then return {@code false}.
     */
    public static boolean isCyrillicWord(String inputData) {
        if (!isFilled(inputData)) {
            return false;
        } else {
            String[] words = inputData.split(" ");
            for (String word : words) {
                if (!word.matches(REG_EX_CYRILLIC_WORD)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Return {@code true} if the argument consists only from number.
     * 
     * @param inputData a String
     * @return {@code true} if the argument consists only from number. Method uses
     *         regular expression for validating argument and
     *         {@code isFilled(String arg)} method.
     */
    public static boolean isNumber(String inputData) {
        if (isFilled(inputData) && inputData.matches(REG_EX_NUMBER)) {
            return true;
        }
        return false;
    }

    /**
     * Return {@code true} if the argument consists only from number.
     * 
     * @param inputData a String
     * @param minValue  an int
     * @param maxValue  an int
     * @return {@code true} if the argument consists only from number and value in
     *         interval from {@code minValue} to {@code maxValue}. Method uses
     *         regular expression for validating argument and
     *         {@code isFilled(String arg)} method.
     */
    public static boolean isIntegerFromInterval(String inputData, int minValue, int maxValue) {
        if (isFilled(inputData) && inputData.matches(REG_EX_NUMBER)) {
            Integer value = Integer.parseInt(inputData);
            if (value >= minValue && value <= maxValue) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return {@code true} if the argument consists only from number.
     * 
     * @param inputData a String
     * @param minValue  a double
     * @param maxValue  a double
     * @return {@code true} if the argument consists only from number, '.', ',' and
     *         value in interval from {@code minValue} to {@code maxValue}. Method
     *         uses regular expression for validating argument and
     *         {@code isFilled(String arg)} method.
     */
    public static boolean isDoubleFromInterval(String inputData, double minValue, double maxValue) {
        if (isFilled(inputData) && inputData.matches(REG_EX_DOUBLE)) {
            Double value = Double.parseDouble(inputData);
            if (value >= minValue && value <= maxValue) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return {@code true} if the argument is validate email address.
     * 
     * @param inputData a String
     * @return {@code true} if the arguments length less than maximum length email
     *         address symbols and if argument is matching of regular expression and
     *         {@code isFilled(String arg)} method.
     */
    public static boolean isEmailAddress(String inputData) {
        if (isFilled(inputData) && inputData.length() <= MAX_EMAIL_ADDRESS_LENGTH
                && inputData.matches(REX_EX_EMAIL_ADDRESS)) {
            return true;
        }
        return false;
    }

    /**
     * Return {@code true} if argument is validate password.
     * 
     * @param inputData a String
     * @return {@code true} if the arguments length less than minimum passwords
     *         length and if argument is matching of regular expression and
     *         {@code isFilled(String arg)} method.
     */
    public static boolean isPassword(String inputData) {
        if (isFilled(inputData) && inputData.length() >= MIN_PASSWORD_LENGTH && inputData.matches(REG_EX_PASSWORD)) {
            return true;
        }
        return false;
    }
    
    /**
     * Return {@code true} if argument is validate {@code LocalDate}.
     * 
     * @param inputData a String
     * @return {@code true} if the arguments length equals date length and if
     *         argument is matching of regular expression and
     *         {@code isFilled(String arg)} method.
     */
    public static boolean isLocalDate(String inputData) {
        if (isFilled(inputData) && inputData.length() == DATE_LENGTH && inputData.matches(REG_EX_DATE)) {
            return true;
        }
        return false;
    }
}