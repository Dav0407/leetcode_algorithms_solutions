package job;

import java.util.ArrayList;
import java.util.List;

public class Question3 {

    public static String encode(List<String> strs) {
        StringBuilder encodedString = new StringBuilder();

        for (String str : strs) {
            for (char c : str.toCharArray())
                encodedString.append((int) c).append(',');

            if (!encodedString.isEmpty())
                encodedString.setLength(encodedString.length() - 1);
            encodedString.append('|');
        }

        if (!encodedString.isEmpty())
            encodedString.setLength(encodedString.length() - 1);

        return encodedString.toString();
    }

    public static List<String> decode(String s) {
        List<String> decodedStrings = new ArrayList<>();
        String[] encodedStrings = s.split("\\|");

        for (String encoded : encodedStrings) {
            StringBuilder decodedString = new StringBuilder();
            String[] asciiValues = encoded.split(",");

            for (String value : asciiValues) {
                try {
                    int ascii = Integer.parseInt(value);
                    decodedString.append((char) ascii);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid ASCII value encountered: " + value);
                }
            }
            decodedStrings.add(decodedString.toString());
        }
        return decodedStrings;
    }

    public static void main(String[] args) {

        List<String> strs = List.of("hello", "my", "name", "is", "Davron", "Normamatov");

        String encodedString = encode(strs);
        System.out.println("Encoded: " + encodedString);

        List<String> strs2 = decode(encodedString);
        System.out.println("Decoded: " + strs2);

        System.out.println("Are strs and strs2 equal? " + strs.equals(strs2));
    }
}
