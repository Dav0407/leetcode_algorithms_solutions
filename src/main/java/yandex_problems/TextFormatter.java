package yandex_problems;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class TextFormatter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();

        List<String> wordArray = splitWordsAndCommas(text);

        int maxLenWord = 0;
        for (String word : wordArray) {
            if (word.length() > maxLenWord && !word.equals(",")) {
                maxLenWord = word.length();
            }
        }

        int maxLineLen = maxLenWord * 3;

        StringBuilder result = new StringBuilder();
        StringBuilder currentLine = new StringBuilder();
        int currentLength = 0;

        for (int i = 0; i < wordArray.size(); i++) {
            String word = wordArray.get(i);

            if (word.equals(",")) {
                // Comma handling
                if (currentLength > 0 && currentLength + 1 <= maxLineLen) {
                    currentLine.append(word);
                    currentLength += 1;
                } else {
                    if (currentLength > 0) {
                        result.append(currentLine).append("\n");
                    }
                    currentLine.setLength(0);
                    currentLine.append(word);
                    currentLength = word.length();
                }
            } else {
                int spaceNeeded = (currentLine.length() > 0 ? 1 : 0) + word.length(); // Account for space

                if (currentLength + spaceNeeded <= maxLineLen) {
                    if (currentLine.length() > 0) {
                        currentLine.append(" ");
                    }
                    currentLine.append(word);
                    currentLength += spaceNeeded;
                } else {
                    result.append(currentLine).append("\n");
                    currentLine.setLength(0);
                    currentLine.append(word);
                    currentLength = word.length();
                }
            }

            // Handle case where comma might be at the end of the text
            if (i == wordArray.size() - 1 && word.equals(",")) {
                result.append(currentLine).append("\n");
            }
        }

        if (!currentLine.isEmpty()) {
            result.append(currentLine);
        }

        System.out.print(result);
    }

    private static List<String> splitWordsAndCommas(String text) {
        List<String> result = new ArrayList<>();
        StringBuilder currentWord = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (ch == ' ' || ch == ',') {
                if (!currentWord.isEmpty()) {
                    result.add(currentWord.toString());
                    currentWord.setLength(0);
                }
                if (ch == ',') result.add(",");
            } else {
                currentWord.append(ch);
            }
        }

        if (!currentWord.isEmpty()) {
            result.add(currentWord.toString());
        }

        return result;
    }
}
