package job;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Question1 {
    public static String charFrequency(String word) {
        Map<Character, Integer> charFrequency = new HashMap<>();
        for (char c : word.toCharArray()) {
            charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
        }
        return word.chars().mapToObj(c -> (char) c)
                .sorted((a, b) -> {
                    int frequencyDifference = charFrequency.get(b).compareTo(charFrequency.get(a));
                    if (frequencyDifference == 0) {
                        return Character.compare(a, b);
                    }
                    return frequencyDifference;
                })
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public static void main(String[] args) {
        String word_1 = "noutbook";
        String word_2 = "Aabb";

        System.out.println(charFrequency(word_1));
        System.out.println(charFrequency(word_2));
    }
}
