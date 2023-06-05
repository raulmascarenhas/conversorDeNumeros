package com.example.conversor;

import java.util.LinkedHashMap;
import java.util.Map;

public class ConversorService {

    private static final Map<Integer, String> ARABIC_TO_ROMAN = new LinkedHashMap<>();

    static {
        ARABIC_TO_ROMAN.put(1000, "M");
        ARABIC_TO_ROMAN.put(900, "CM");
        ARABIC_TO_ROMAN.put(500, "D");
        ARABIC_TO_ROMAN.put(400, "CD");
        ARABIC_TO_ROMAN.put(100, "C");
        ARABIC_TO_ROMAN.put(90, "XC");
        ARABIC_TO_ROMAN.put(50, "L");
        ARABIC_TO_ROMAN.put(40, "XL");
        ARABIC_TO_ROMAN.put(10, "X");
        ARABIC_TO_ROMAN.put(9, "IX");
        ARABIC_TO_ROMAN.put(5, "V");
        ARABIC_TO_ROMAN.put(4, "IV");
        ARABIC_TO_ROMAN.put(1, "I");
    }

    public static int convertRomanToArabic(String roman) {
        int result = 0;
        int prevValue = 0;
        int repetitions = 0;
        for (int i = roman.length() - 1; i >= 0; i--) {
            char current = roman.charAt(i);
            int currentValue = getValueOfRomanDigit(current);

            if (currentValue >= prevValue) {
                if (currentValue == prevValue) {
                    repetitions++;
                    if (repetitions > 3) {
                        throw new IllegalArgumentException("Invalid Roman numeral");
                    }
                } else {
                    repetitions = 1;
                }
                result += currentValue;
            } else {
                result -= currentValue;
                repetitions = 0;
            }

            prevValue = currentValue;
        }
        return result;
    }

    public static String convertArabicToRoman(int arabic) {
        if (arabic <= 0 || arabic >= 4000) {
            throw new IllegalArgumentException("Number out of range");
        }

        StringBuilder result = new StringBuilder();
        for (Map.Entry<Integer, String> entry : ARABIC_TO_ROMAN.entrySet()) {
            int value = entry.getKey();
            String symbol = entry.getValue();

            while (arabic >= value) {
                result.append(symbol);
                arabic -= value;
            }
        }
        return result.toString();
    }

    private static int getValueOfRomanDigit(char digit) {
        switch (digit) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                throw new IllegalArgumentException("Invalid Roman digit");
        }
    }
}
