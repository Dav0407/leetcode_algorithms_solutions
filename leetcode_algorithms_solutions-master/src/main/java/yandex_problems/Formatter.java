package yandex_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Formatter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();

        List<String> wordList = splitWordsAndCommas(text);
        int longestWordLen = 0;
        for (String word : wordList) {
            if(word.length() > longestWordLen && !word.equals(","))
                longestWordLen = word.length();
        }

        int lineLengthLimit = longestWordLen * 3;
        int currentLineLength = 0;
        StringBuilder currentLine = new StringBuilder();
        StringBuilder result = new StringBuilder();

    }

    private static List<String> splitWordsAndCommas(String text) {
        dub(text);
        List<String> result = new ArrayList<>();
        System.out.println(result);
        return result;
    }

    static void dub(String text) {
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

        if (!currentWord.isEmpty())
            result.add(currentWord.toString());
    }
}
